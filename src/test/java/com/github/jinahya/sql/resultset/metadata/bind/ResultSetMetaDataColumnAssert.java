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

    static ResultSetMetaDataColumnAssert assertThatColumn(final ResultSetMetaDataColumn actual) {
        return new ResultSetMetaDataColumnAssert(actual);
    }

    // -----------------------------------------------------------------------------------------------------------------
    ResultSetMetaDataColumnAssert(final ResultSetMetaDataColumn actual) {
        super(actual, ResultSetMetaDataColumnAssert.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    ResultSetMetaDataColumnAssert hasCatalogName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getCatalogName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnClassName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnClassName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnDisplaySize(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnDisplaySize()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnLabel(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnLabel()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnType(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnType()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasColumnTypeName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getColumnTypeName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasPrecision(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getPrecision()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasScale(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getScale()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasSchemaName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getSchemaName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasTableName(final String expected) {
        return isNotNull().satisfies(a -> assertThat(a.getTableName()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasAutoIncrement(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isAutoIncrement()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasCaseSensitive(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isCaseSensitive()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasCurrency(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isCurrency()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasDefinitelyWritable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isDefinitelyWritable()).isEqualTo(expected));
    }

    // -----------------------------------------------------------------------------------------------------------------
    ResultSetMetaDataColumnAssert hasNullable(final int expected) {
        return isNotNull().satisfies(a -> assertThat(a.getNullable()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasValidNullable() {
        return isNotNull().satisfies(a -> assertThat(a.getNullable()).isIn(
                ResultSetMetaData.columnNoNulls,
                ResultSetMetaData.columnNullable,
                ResultSetMetaData.columnNullableUnknown
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    ResultSetMetaDataColumnAssert hasReadOnly(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isReadOnly()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasSearchable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isSearchable()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasSigned(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isSigned()).isEqualTo(expected));
    }

    ResultSetMetaDataColumnAssert hasWritable(final boolean expected) {
        return isNotNull().satisfies(a -> assertThat(a.isWritable()).isEqualTo(expected));
    }
}
