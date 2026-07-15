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
import org.junit.jupiter.api.Test;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests {@link JakartaXmlBindingUtils}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class JakartaXmlBindingUtilsTest {

    /**
     * Verifies that {@link JakartaXmlBindingUtils#generateSchema(JAXBContext)} rejects a {@code null} context.
     */
    @Test
    void generateSchema_NullPointerException_NullContext() {
        assertThatThrownBy(() -> JakartaXmlBindingUtils.generateSchema(null))
                .isInstanceOf(NullPointerException.class);
    }

    /**
     * Verifies that {@link JakartaXmlBindingUtils#generateSchema(JAXBContext)} returns a non-{@code null} schema for a
     * context bound to this package.
     *
     * @throws Exception if the test fails
     */
    @Test
    void generateSchema_NonNull_Context() throws Exception {
        final var context = JAXBContext.newInstance(getClass().getPackageName());
        final var schema = JakartaXmlBindingUtils.generateSchema(context);
        assertThat(schema).isNotNull();
    }

    /**
     * Verifies that {@link JakartaXmlBindingUtils#generateSchema()} returns a non-{@code null} schema.
     *
     * @throws Exception if the test fails
     */
    @Test
    void generateSchema_NonNull_() throws Exception {
        final var schema = JakartaXmlBindingUtils.generateSchema();
        assertThat(schema).isNotNull();
    }

    /**
     * Verifies that each invocation of {@link JakartaXmlBindingUtils#generateSchema()} returns a fresh schema instance.
     *
     * @throws Exception if the test fails
     */
    @Test
    void generateSchema_NewInstance_() throws Exception {
        final var schema1 = JakartaXmlBindingUtils.generateSchema();
        final var schema2 = JakartaXmlBindingUtils.generateSchema();
        assertThat(schema1).isNotSameAs(schema2);
    }

    /**
     * Verifies that the schema returned by {@link JakartaXmlBindingUtils#generateSchema()} validates the XML marshalled
     * from a {@link ResultSetMetaDataColumnWrapper}.
     *
     * @throws Exception if the test fails
     */
    @Test
    void generateSchema_ValidatesMarshalledWrapper_() throws Exception {
        final var context = JAXBContext.newInstance(getClass().getPackageName());
        final var column = new ResultSetMetaDataColumn();
        column.setCatalogName("CATALOG");
        column.setColumnClassName(String.class.getName());
        column.setColumnLabel("COLUMN");
        column.setColumnName("COLUMN");
        column.setColumnTypeName("VARCHAR");
        column.setSchemaName("SCHEMA");
        column.setTableName("TABLE");
        final var wrapper = ResultSetMetaDataColumnWrapper.of(List.of(column));
        final var writer = new StringWriter();
        context.createMarshaller().marshal(wrapper, writer);
        final var xml = writer.toString();

        final Schema schema = JakartaXmlBindingUtils.generateSchema();
        assertThatCode(() -> schema.newValidator().validate(new StreamSource(new StringReader(xml))))
                .doesNotThrowAnyException();
    }
}
