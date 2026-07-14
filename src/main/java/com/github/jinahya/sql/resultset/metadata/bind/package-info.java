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

/**
 * Defines types for binding column metadata exposed by {@link java.sql.ResultSetMetaData}.
 * <h2>Jakarta XML Binding</h2>
 * <p>
 * XML binding uses field access and namespace-qualified elements. The package namespace is
 * {@link com.github.jinahya.sql.resultset.metadata.bind.JakartaXmlBindingConstants#NAMESPACE_URI}. Lists can be
 * represented as XML documents with
 * {@link com.github.jinahya.sql.resultset.metadata.bind.ResultSetMetaDataColumnWrapper}.
 * <h2>Jakarta JSON Binding</h2>
 * <p>
 * JSON binding also uses field access. The package-level
 * {@link jakarta.json.bind.annotation.JsonbVisibility @JsonbVisibility} lets a default {@link jakarta.json.bind.Jsonb}
 * instance serialize and deserialize binding types despite their private fields and package-private setters.
 *
 * @author Jin Kwon &lt; /*- #%L resultset-metadata-bind %% Copyright (C) 2016 - 2026 Jinahya, Inc. %% Licensed
 *         under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *         the License. You may obtain a copy of the License at
 *         <p>
 *         http://www.apache.org/licenses/LICENSE-2.0
 *         <p>
 *         Unless required by applicable law or agreed to in writing, software distributed under the License is
 *         distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 *         the License for the specific language governing permissions and limitations under the License. #L%
 */

import com.github.jinahya.sql.resultset.metadata.bind.JakartaJsonBindingUtils;
import com.github.jinahya.sql.resultset.metadata.bind.JakartaXmlBindingConstants;
import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

onacit_at_gmail.com&gt;
 *@see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.sql/java/sql/ResultSetMetaData.html">
        *{@code java.sql.ResultSetMetaData}</a>
        */
// ------------------------------------------------------------------------------------------------- Jakarta XML Binding
@XmlSchema(
        namespace = JakartaXmlBindingConstants.NAMESPACE_URI,
        elementFormDefault = XmlNsForm.QUALIFIED,
        attributeFormDefault = XmlNsForm.UNQUALIFIED,
        xmlns = {
                @XmlNs(prefix = JakartaXmlBindingConstants.NAMESPACE_PREFIX,
                       namespaceURI = JakartaXmlBindingConstants.NAMESPACE_URI)
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
// ------------------------------------------------------------------------------------------------ Jakarta JSON Binding
@JsonbVisibility(JakartaJsonBindingUtils.FieldAccessVisibilityStrategy.class)
package com.github.jinahya.sql.resultset.metadata.bind;

import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;
