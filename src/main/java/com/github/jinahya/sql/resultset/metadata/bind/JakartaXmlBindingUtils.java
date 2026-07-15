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
import jakarta.xml.bind.SchemaOutputResolver;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.util.Objects;

/**
 * Package-private utilities for Jakarta XML Binding.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JakartaXmlBindingUtils {

    /**
     * Creates a new JAXB context bound to this package's binding types.
     *
     * @return a new {@link JAXBContext} for this package's binding types
     * @throws JAXBException if creating the context fails
     * @see JAXBContext#newInstance(String)
     */
    static JAXBContext getContext() throws JAXBException {
        return JAXBContext.newInstance(JakartaXmlBindingUtils.class.getPackageName());
    }

    /**
     * Generates a {@link Schema} describing the XML representation of the binding types bound by the specified JAXB
     * context.
     *
     * @param context the JAXB context whose schema is generated
     * @return a new {@link Schema} generated from {@code context}
     * @throws IOException   if generating the schema fails
     * @throws SAXException  if the generated schema cannot be parsed
     * @throws JAXBException if an error occurs while generating the schema
     * @see JAXBContext#generateSchema(SchemaOutputResolver)
     */
    static Schema generateSchema(final JAXBContext context) throws IOException, SAXException, JAXBException {
        Objects.requireNonNull(context, "context is null");
        final var result = new DOMResult();
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri, final String suggestedFileName) {
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new DOMSource(result.getNode()));
    }

    /**
     * Generates a {@link Schema} describing the XML representation of this package's binding types.
     *
     * @return a new {@link Schema} for this package's binding types
     * @throws IOException   if generating the schema fails
     * @throws SAXException  if the generated schema cannot be parsed
     * @throws JAXBException if creating the context or generating the schema fails
     */
    static Schema generateSchema() throws IOException, SAXException, JAXBException {
        return generateSchema(getContext());
    }

    private JakartaXmlBindingUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
