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

import org.junit.jupiter.api.Test;

import java.sql.ResultSetMetaData;

import static com.github.jinahya.sql.resultset.metadata.bind.ResultSetMetaDataColumnAssert.assertThatColumn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Tests for {@link ResultSetMetaDataColumn}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class ResultSetMetaDataColumnTest {

    /**
     * Verifies that {@link ResultSetMetaDataColumn} declares a field, an accessor, and a setter of the matching type
     * for every {@link ResultSetMetaData} binding method.
     *
     * @throws Exception if the class cannot be introspected or reflected upon
     */
    @Test
    void definesPropertyForEachResultSetMetaDataColumnMethod() throws Exception {
        final var properties = ResultSetMetaDataColumnTestUtils.properties();
        final var methods = ResultSetMetaDataTestUtils.getBindingMethodList();
        assertThat(methods).isNotEmpty();
        for (final var metadataMethod : methods) {
            final var prefixLength = metadataMethod.getName().startsWith("is") ? 2 : 3;
            final var propertyName = ResultSetMetaDataColumnTestUtils.propertyName(metadataMethod);
            final var field = ResultSetMetaDataColumn.class.getDeclaredField(propertyName);
            assertThat(field.getType())
                    .as("field type for ResultSetMetaData.%s(int)", metadataMethod.getName())
                    .isEqualTo(metadataMethod.getReturnType());
            assertThat(properties).as("bean properties")
                    .containsKey(propertyName);
            final var property = properties.get(propertyName);
            assertThat(property.getReadMethod())
                    .as("read method for property '%s'", propertyName)
                    .isNotNull();
            assertThat(property.getReadMethod().getReturnType())
                    .as("accessor return type for property '%s'", propertyName)
                    .isEqualTo(metadataMethod.getReturnType());
            final var setterName = "set" + metadataMethod.getName().substring(prefixLength);
            final var setter = ResultSetMetaDataColumn.class.getDeclaredMethod(
                    setterName,
                    metadataMethod.getReturnType()
            );
            assertThat(setter.getReturnType())
                    .as("setter return type for property '%s'", propertyName)
                    .isEqualTo(void.class);
        }
    }

    /**
     * Verifies that {@link ResultSetMetaDataColumn#bind(ResultSetMetaData, int)} invokes every
     * {@link ResultSetMetaData} binding method exactly once for the requested column and assigns each returned value to
     * the matching property.
     *
     * @throws Exception if the class cannot be introspected or reflected upon
     */
    @Test
    void bindReadsAndAssignsEveryResultSetMetaDataColumnMethod() throws Exception {
        final var column = 7;
        final var methods = ResultSetMetaDataTestUtils.getBindingMethodList();
        final var metadata = mock(ResultSetMetaData.class, invocation -> {
            if (methods.contains(invocation.getMethod())) {
                return ResultSetMetaDataTestUtils.valueFor(invocation.getMethod());
            }
            return RETURNS_DEFAULTS.answer(invocation);
        });

        final var actual = ResultSetMetaDataColumn.bind(metadata, column);
        final var properties = ResultSetMetaDataColumnTestUtils.properties();
        for (final var method : methods) {
            final var expected = ResultSetMetaDataTestUtils.valueFor(method);
            final var property = properties.get(ResultSetMetaDataColumnTestUtils.propertyName(method));
            assertThat(property.getReadMethod().invoke(actual))
                    .as("value copied from ResultSetMetaData.%s(int)", method.getName())
                    .isEqualTo(expected);
            method.invoke(verify(metadata, times(1)), column);
        }
        verifyNoMoreInteractions(metadata);
    }

    /**
     * Verifies that a failing {@link ResultSetMetaDataColumnAssert} reports both the expected and actual values.
     */
    @Test
    void assertionReportsMismatch() {
        final var actual = new ResultSetMetaDataColumn();
        actual.setColumnName("actual");
        assertThatThrownBy(() -> assertThatColumn(actual).hasColumnName("expected"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("expected")
                .hasMessageContaining("actual");
    }

    /**
     * Verifies that {@link ResultSetMetaDataColumnAssert#hasValidNullable()} accepts every {@code nullable} constant
     * defined by {@link ResultSetMetaData}.
     */
    @Test
    void hasValidNullableAcceptsAllJdbcConstants() {
        final var actual = new ResultSetMetaDataColumn();
        for (final var nullable : new int[]{
                ResultSetMetaData.columnNoNulls,
                ResultSetMetaData.columnNullable,
                ResultSetMetaData.columnNullableUnknown
        }) {
            actual.setNullable(nullable);
            assertThatColumn(actual).hasValidNullable();
        }
    }

    /**
     * Verifies that {@link ResultSetMetaDataColumnAssert#hasValidNullable()} rejects a {@code nullable} value that is
     * not a {@link ResultSetMetaData} constant.
     */
    @Test
    void hasValidNullableRejectsUnknownValue() {
        final var actual = new ResultSetMetaDataColumn();
        actual.setNullable(Integer.MIN_VALUE);

        assertThatThrownBy(() -> assertThatColumn(actual).hasValidNullable())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(Integer.toString(Integer.MIN_VALUE));
    }
}
