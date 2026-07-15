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

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Test utilities for {@link ResultSetMetaDataColumn}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ResultSetMetaDataColumnTestUtils {

    /**
     * Returns the bean properties declared by {@link ResultSetMetaDataColumn}, excluding those inherited from
     * {@link Object}, mapped by property name.
     *
     * @return a map of property name to property descriptor
     * @throws Exception if the class cannot be introspected
     */
    static Map<String, PropertyDescriptor> properties() throws Exception {
        return Arrays.stream(
                        Introspector.getBeanInfo(
                                ResultSetMetaDataColumn.class, Object.class
                        ).getPropertyDescriptors()
                )
                .collect(Collectors.toMap(PropertyDescriptor::getName, Function.identity()));
    }

    /**
     * Returns the {@link ResultSetMetaDataColumn} property name corresponding to the specified
     * {@link java.sql.ResultSetMetaData} binding method, by stripping its {@code get}/{@code is} prefix and
     * decapitalizing the remainder.
     *
     * @param method the binding method whose property name is derived
     * @return the corresponding bean property name
     */
    static String propertyName(final Method method) {
        final var prefixLength = method.getName().startsWith("is") ? 2 : 3;
        return Introspector.decapitalize(method.getName().substring(prefixLength));
    }

    private ResultSetMetaDataColumnTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
