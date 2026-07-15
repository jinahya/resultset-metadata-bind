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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Test utilities for {@link ResultSetMetaData} binding methods.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ResultSetMetaDataTestUtils {

    /**
     * Returns a distinct sentinel value for the specified binding method, derived from the method name so that each
     * method maps to a recognizable value of its return type.
     *
     * @param method the binding method to produce a value for
     * @return a sentinel value assignable to the method's return type
     * @throws AssertionError if the method's return type is neither {@code String}, {@code int}, nor {@code boolean}
     */
    static Object valueFor(final Method method) {
        if (method.getReturnType() == String.class) {
            return method.getName();
        }
        if (method.getReturnType() == int.class) {
            return method.getName().hashCode();
        }
        if (method.getReturnType() == boolean.class) {
            return (method.getName().hashCode() & 1) == 0;
        }
        throw new AssertionError("unexpected return type: " + method);
    }

    /**
     * Streams the binding methods of {@link ResultSetMetaData}, that is, its non-static methods accepting a single
     * {@code int} column-index argument.
     *
     * @return a stream of {@link ResultSetMetaData} binding methods
     */
    private static Stream<Method> getBindingMethodStream() {
        return Arrays.stream(ResultSetMetaData.class.getMethods())
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .filter(m -> Arrays.equals(m.getParameterTypes(), new Class<?>[]{int.class}));
    }

    /**
     * Returns the binding methods of {@link ResultSetMetaData} as a list.
     *
     * @return a list of {@link ResultSetMetaData} binding methods
     * @see #getBindingMethodStream()
     */
    static List<Method> getBindingMethodList() {
        return getBindingMethodStream().toList();
    }

    private ResultSetMetaDataTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
