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

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * An XML document wrapper for a list of {@link ResultSetMetaDataColumn} values.
 * <p>
 * Jakarta XML Binding cannot marshal a bare {@link List} as a document. This wrapper supplies a root element and maps
 * each wrapped value to a {@code resultSetMetaDataColumn} child element.
 */
@XmlRootElement(name = ResultSetMetaDataColumnWrapper.ROOT_ELEMENT_NAME)
public class ResultSetMetaDataColumnWrapper {

    /**
     * The wrapper's XML root-element name.
     */
    public static final String ROOT_ELEMENT_NAME = "resultSetMetaDataColumns";

    static ResultSetMetaDataColumnWrapper of(final List<ResultSetMetaDataColumn> elements) {
        final var instance = new ResultSetMetaDataColumnWrapper();
        instance.setElements(elements);
        return instance;
    }

    /**
     * Creates an empty wrapper for binding frameworks and subclasses.
     */
    protected ResultSetMetaDataColumnWrapper() {
        super();
    }

    /**
     * Returns the wrapped elements.
     *
     * @return a non-{@code null}, modifiable list of wrapped elements
     */
    public List<ResultSetMetaDataColumn> getElements() {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        return elements;
    }

    void setElements(final List<ResultSetMetaDataColumn> elements) {
        this.elements = elements;
    }

    @XmlElement(name = "resultSetMetaDataColumn")
    private List<ResultSetMetaDataColumn> elements;
}
