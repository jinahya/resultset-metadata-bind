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

import org.assertj.core.api.AbstractAssert;

import java.sql.ResultSetMetaData;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AssertJ assertions for {@link ResultSetMetaDataColumn}.
 */
final class ResultSetMetaDataColumnAssert
        extends AbstractAssert<ResultSetMetaDataColumnAssert, ResultSetMetaDataColumn> {

    /**
     * Creates a new assertion for the specified column.
     *
     * @param actual the column under test
     * @return a new assertion for {@code actual}
     */
    static ResultSetMetaDataColumnAssert assertThatColumn(final ResultSetMetaDataColumn actual) {
        return new ResultSetMetaDataColumnAssert(actual);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for the specified column.
     *
     * @param actual the column under test
     */
    ResultSetMetaDataColumnAssert(final ResultSetMetaDataColumn actual) {
        super(actual, ResultSetMetaDataColumnAssert.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Asserts that the column under test has the specified {@code catalogName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasCatalogName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getCatalogName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnClassName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnClassName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnClassName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnDisplaySize}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnDisplaySize(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnDisplaySize()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnLabel}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnLabel(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnLabel()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnType}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnType(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnType()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code columnTypeName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasColumnTypeName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnTypeName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code precision}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasPrecision(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getPrecision()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code scale}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasScale(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getScale()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code schemaName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasSchemaName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getSchemaName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code tableName}.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasTableName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getTableName()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code autoIncrement} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasAutoIncrement(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isAutoIncrement()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code caseSensitive} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasCaseSensitive(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isCaseSensitive()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code currency} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasCurrency(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isCurrency()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code definitelyWritable} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasDefinitelyWritable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isDefinitelyWritable()).isEqualTo(expected));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Asserts that the column under test has the specified {@code nullable} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasNullable(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getNullable()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has a {@code nullable} value that is one of the {@code columnNoNulls},
     * {@code columnNullable}, or {@code columnNullableUnknown} constants defined in {@link ResultSetMetaData}.
     *
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasValidNullable() {
        return isNotNull().satisfies(a -> assertThat(a.getNullable()).isIn(
                ResultSetMetaData.columnNoNulls,
                ResultSetMetaData.columnNullable,
                ResultSetMetaData.columnNullableUnknown
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Asserts that the column under test has the specified {@code readOnly} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasReadOnly(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isReadOnly()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code searchable} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasSearchable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isSearchable()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code signed} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasSigned(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isSigned()).isEqualTo(expected));
    }

    /**
     * Asserts that the column under test has the specified {@code writable} value.
     *
     * @param expected the expected value
     * @return this assertion
     */
    ResultSetMetaDataColumnAssert hasWritable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isWritable()).isEqualTo(expected));
    }
}
