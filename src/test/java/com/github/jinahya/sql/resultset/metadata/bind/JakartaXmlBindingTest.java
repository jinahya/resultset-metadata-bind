/*
 * Copyright 2016 Jin Kwon &lt;onacit_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.sql.resultset.metadata.bind;

/*-
 * #%L
 * resultset-metadata-bind
 * %%
 * Copyright (C) 2016 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests Jakarta XML Binding support for the package's binding types.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class JakartaXmlBindingTest {

    /**
     * Verifies that a {@link ResultSetMetaDataColumnWrapper} marshals to schema-valid XML and unmarshals back to an
     * equivalent list of columns.
     *
     * @param tempDir a temporary directory for the intermediate XML and schema files
     * @throws Exception if marshalling, schema generation, validation, or unmarshalling fails
     */
    @Test
    void wrapperRoundTripsList(@TempDir final Path tempDir) throws Exception {
        final var first = new ResultSetMetaDataColumn();
        first.setCatalogName("CATALOG");
        first.setColumnClassName(String.class.getName());
        first.setColumnLabel("FIRST");
        first.setColumnName("FIRST");
        first.setColumnTypeName("VARCHAR");
        first.setSchemaName("SCHEMA");
        first.setTableName("TABLE");
        final var second = new ResultSetMetaDataColumn();
        second.setCatalogName("CATALOG");
        second.setColumnClassName(String.class.getName());
        second.setColumnLabel("SECOND");
        second.setColumnName("SECOND");
        second.setColumnTypeName("VARCHAR");
        second.setSchemaName("SCHEMA");
        second.setTableName("TABLE");
        final var expected = ResultSetMetaDataColumnWrapper.of(List.of(first, second));
        final var context = JAXBContext.newInstance(getClass().getPackageName());
        final var path = tempDir.resolve("columns.xml");
        context.createMarshaller().marshal(expected, path.toFile());

        final var schema = tempDir.resolve("columns.xsd");
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri, final String suggestedFileName) {
                final var result = new StreamResult(schema.toFile());
                result.setSystemId(schema.toUri().toString());
                return result;
            }
        });
        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(schema.toFile())
                .newValidator()
                .validate(new StreamSource(path.toFile()));

        final var object = context.createUnmarshaller().unmarshal(path.toFile());
        assertThat(object).isInstanceOf(ResultSetMetaDataColumnWrapper.class);
        final var actual = (ResultSetMetaDataColumnWrapper) object;
        assertThat(actual.getElements())
                .hasSize(2)
                .allSatisfy(e -> assertThat(e).isInstanceOf(ResultSetMetaDataColumn.class));
        assertThat(actual.getElements())
                .extracting(ResultSetMetaDataColumn::getColumnName)
                .containsExactly("FIRST", "SECOND");
    }

    /**
     * Marshals the supplied Jakarta XML Binding element to the standard output stream.
     *
     * @param jaxbElement the element to marshal
     * @throws JAXBException if the binding context or marshaller cannot process the element
     */
    static void marshalInstance(final Object jaxbElement) throws JAXBException {
        final var context = JAXBContext.newInstance(
                JakartaXmlBindingTest.class.getPackage().getName()
        );
        final var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbElement, System.out);
    }

    /**
     * Generates the package's XML Schema and writes it to the standard output stream.
     *
     * @throws JAXBException if the binding context cannot be created
     * @throws IOException   if schema generation fails
     */
    @Test
    public void printSchema() throws JAXBException, IOException {
        final var context = JAXBContext.newInstance(getClass().getPackage().getName());
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                    throws IOException {
                return new StreamResult(System.out) {
                    @Override
                    public String getSystemId() {
                        return "noid";
                    }
                };
            }
        });
    }
}
