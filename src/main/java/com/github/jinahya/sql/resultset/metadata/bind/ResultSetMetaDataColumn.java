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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Represents metadata for a single column described by {@link ResultSetMetaData}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlRootElement
public class ResultSetMetaDataColumn {

    /**
     * Binds the specified column from the supplied result-set metadata.
     *
     * @param metadata the result-set metadata from which the column is read
     * @param column   the one-based index of the column to bind
     * @return metadata bound from the specified column
     * @throws SQLException if a database access error occurs
     */
    public static ResultSetMetaDataColumn bind(final ResultSetMetaData metadata, final int column) throws SQLException {
        final var bound = new ResultSetMetaDataColumn();
        bound.setCatalogName(metadata.getCatalogName(column));
        bound.setColumnClassName(metadata.getColumnClassName(column));
        bound.setColumnDisplaySize(metadata.getColumnDisplaySize(column));
        bound.setColumnLabel(metadata.getColumnLabel(column));
        bound.setColumnName(metadata.getColumnName(column));
        bound.setColumnType(metadata.getColumnType(column));
        bound.setColumnTypeName(metadata.getColumnTypeName(column));
        bound.setPrecision(metadata.getPrecision(column));
        bound.setScale(metadata.getScale(column));
        bound.setSchemaName(metadata.getSchemaName(column));
        bound.setTableName(metadata.getTableName(column));
        bound.setAutoIncrement(metadata.isAutoIncrement(column));
        bound.setCaseSensitive(metadata.isCaseSensitive(column));
        bound.setCurrency(metadata.isCurrency(column));
        bound.setDefinitelyWritable(metadata.isDefinitelyWritable(column));
        bound.setNullable(metadata.isNullable(column));
        bound.setReadOnly(metadata.isReadOnly(column));
        bound.setSearchable(metadata.isSearchable(column));
        bound.setSigned(metadata.isSigned(column));
        bound.setWritable(metadata.isWritable(column));
        return bound;
    }

    /**
     * Binds metadata for every column described by the supplied result-set metadata and passes each bound column to the
     * specified consumer, in column-index order.
     *
     * @param metadata the result-set metadata from which the columns are read
     * @param consumer the consumer that accepts each bound column
     * @throws SQLException if a database access error occurs
     */
    public static void bind(final ResultSetMetaData metadata, final Consumer<? super ResultSetMetaDataColumn> consumer)
            throws SQLException {
        final var columnCount = metadata.getColumnCount();
        for (var i = 1; i <= columnCount; i++) {
            consumer.accept(bind(metadata, i));
        }
    }

    /**
     * Binds metadata for every column described by the supplied result-set metadata.
     *
     * @param metadata the result-set metadata from which the columns are read
     * @return the bound column metadata, in column-index order
     * @throws SQLException if a database access error occurs
     */
    public static List<ResultSetMetaDataColumn> bind(final ResultSetMetaData metadata) throws SQLException {
        final var columnCount = metadata.getColumnCount();
        final var bound = new ArrayList<ResultSetMetaDataColumn>(columnCount);
        bind(metadata, bound::add);
        return bound;
    }

    /**
     * Binds metadata for every column in the supplied result set and passes each bound column to the specified
     * consumer, in column-index order.
     *
     * @param results  the result set whose column metadata is bound
     * @param consumer the consumer that accepts each bound column
     * @throws SQLException if a database access error occurs
     */
    public static void bind(final ResultSet results, final Consumer<? super ResultSetMetaDataColumn> consumer)
            throws SQLException {
        bind(results.getMetaData(), consumer);
    }

    /**
     * Binds metadata for every column in the supplied result set.
     *
     * @param results the result set whose metadata is bound
     * @return the bound column metadata, in column-index order
     * @throws SQLException if a database access error occurs
     */
    public static List<ResultSetMetaDataColumn> bind(final ResultSet results) throws SQLException {
        return bind(results.getMetaData());
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates an empty instance for binding frameworks and subclasses.
     */
    protected ResultSetMetaDataColumn() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{'
               + "catalogName=" + catalogName
               + ",columnClassName=" + columnClassName
               + ",columnDisplaySize=" + columnDisplaySize
               + ",columnLabel=" + columnLabel
               + ",columnName=" + columnName
               + ",columnType=" + columnType
               + ",columnTypeName=" + columnTypeName
               + ",precision=" + precision
               + ",scale=" + scale
               + ",schemaName=" + schemaName
               + ",tableName=" + tableName
               + ",autoIncrement=" + autoIncrement
               + ",caseSensitive=" + caseSensitive
               + ",currency=" + currency
               + ",definitelyWritable=" + definitelyWritable
               + ",nullable=" + nullable
               + ",readOnly=" + readOnly
               + ",searchable=" + searchable
               + ",signed=" + signed
               + ",writable=" + writable
               + '}';
    }

    // ----------------------------------------------------------------------------------------------------- catalogName

    /**
     * Returns the value of {@code catalogName}.
     *
     * @return the value of {@code catalogName}.
     * @see ResultSetMetaData#getCatalogName(int)
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * Sets the value of {@code catalogName}.
     *
     * @param catalogName new value for {@code catalogName}
     * @see ResultSetMetaData#getCatalogName(int)
     */
    void setCatalogName(final String catalogName) {
        this.catalogName = catalogName;
    }

    // ------------------------------------------------------------------------------------------------- columnClassName

    /**
     * Returns the value of {@code columnClassName}.
     *
     * @return the value of {@code columnClassName}.
     * @see ResultSetMetaData#getColumnClassName(int)
     */
    public String getColumnClassName() {
        return columnClassName;
    }

    /**
     * Sets the value of {@code columnClassName}.
     *
     * @param columnClassName new value for {@code columnClassName}.
     * @see ResultSetMetaData#getColumnClassName(int)
     */
    void setColumnClassName(final String columnClassName) {
        this.columnClassName = columnClassName;
    }

    // ----------------------------------------------------------------------------------------------- columnDisplaySize

    /**
     * Returns the value of {@code columnDisplaySize}.
     *
     * @return the value of {@code columnDisplaySize}.
     * @see ResultSetMetaData#getColumnDisplaySize(int)
     */
    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    /**
     * Sets the value of {@code columnDisplaySize}.
     *
     * @param columnDisplaySize new value for {@code columnDisplaySize}.
     * @see ResultSetMetaData#getColumnDisplaySize(int)
     */
    void setColumnDisplaySize(final int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    // ----------------------------------------------------------------------------------------------------- columnLabel

    /**
     * Returns the value of {@code columnLabel}.
     *
     * @return the value of {@code columnLabel}.
     * @see ResultSetMetaData#getColumnLabel(int)
     */
    public String getColumnLabel() {
        return columnLabel;
    }

    /**
     * Sets the value of {@code columnLabel}.
     *
     * @param columnLabel new value for {@code columnLabel}
     * @see ResultSetMetaData#getColumnLabel(int)
     */
    void setColumnLabel(final String columnLabel) {
        this.columnLabel = columnLabel;
    }

    // ------------------------------------------------------------------------------------------------------ columnName

    /**
     * Returns the value of {@code columnName}.
     *
     * @return the value of {@code columnName}.
     * @see ResultSetMetaData#getColumnName(int)
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets the value of {@code columnName}.
     *
     * @param columnName the value of {@code columnName}.
     * @see ResultSetMetaData#getColumnName(int)
     */
    void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    // ------------------------------------------------------------------------------------------------------ columnType

    /**
     * Returns the value of {@code columnType}.
     *
     * @return the value of {@code columnType}
     * @see ResultSetMetaData#getColumnType(int)
     */
    public int getColumnType() {
        return columnType;
    }

    /**
     * Sets the value of {@code columnType}.
     *
     * @param columnType new value for {@code columnType}
     * @see ResultSetMetaData#getColumnType(int)
     */
    void setColumnType(final int columnType) {
        this.columnType = columnType;
    }

    // -------------------------------------------------------------------------------------------------- columnTypeName

    /**
     * Returns the value of {@code columnTypeName}.
     *
     * @return the value of {@code columnTypeName}.
     * @see ResultSetMetaData#getColumnTypeName(int)
     */
    public String getColumnTypeName() {
        return columnTypeName;
    }

    /**
     * Sets the value of {@code columnTypeName}.
     *
     * @param columnTypeName new value for {@code columnTypeName}
     * @see ResultSetMetaData#getColumnTypeName(int)
     */
    void setColumnTypeName(final String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    // ------------------------------------------------------------------------------------------------------- precision

    /**
     * Returns the value of {@code precision}.
     *
     * @return the value of {@code precision}.
     * @see ResultSetMetaData#getPrecision(int)
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Sets the value of {@code precision}.
     *
     * @param precision new value for {@code precision}
     * @see ResultSetMetaData#getPrecision(int)
     */
    void setPrecision(final int precision) {
        this.precision = precision;
    }

    // ----------------------------------------------------------------------------------------------------------- scale

    /**
     * Returns the value of {@code scale}.
     *
     * @return the value of {@code scale}.
     * @see ResultSetMetaData#getScale(int)
     */
    public int getScale() {
        return scale;
    }

    /**
     * Sets the value of {@code scale}.
     *
     * @param scale new value for {@code scale}.
     * @see ResultSetMetaData#getScale(int)
     */
    void setScale(final int scale) {
        this.scale = scale;
    }

    // ------------------------------------------------------------------------------------------------------ schemaName

    /**
     * Returns the value of {@code schemaName}.
     *
     * @return the value of {@code schemaName}.
     * @see ResultSetMetaData#getSchemaName(int)
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * Sets the value of {@code schemaName}.
     *
     * @param schemaName new value for {@code schemaName}.
     * @see ResultSetMetaData#getSchemaName(int)
     */
    void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    // ------------------------------------------------------------------------------------------------------- tableName

    /**
     * Returns the value of {@code tableName}.
     *
     * @return the value of {@code tableName}.
     * @see ResultSetMetaData#getTableName(int)
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of {@code tableName}.
     *
     * @param tableName new value for {@code tableName}
     * @see ResultSetMetaData#getTableName(int)
     */
    void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    // --------------------------------------------------------------------------------------------------- autoIncrement

    /**
     * Returns the value of {@code autoIncrement}.
     *
     * @return the value of {@code autoIncrement}.
     * @see ResultSetMetaData#isAutoIncrement(int)
     */
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    /**
     * Sets the value of {@code autoIncrement}.
     *
     * @param autoIncrement the new value of {@code autoIncrement}
     * @see ResultSetMetaData#isAutoIncrement(int)
     */
    void setAutoIncrement(final boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    // --------------------------------------------------------------------------------------------------- caseSensitive

    /**
     * Returns the value of {@code caseSensitive}.
     *
     * @return the value of {@code caseSensitive}
     * @see ResultSetMetaData#isCaseSensitive(int)
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Sets the value of {@code caseSensitive}.
     *
     * @param caseSensitive the new value of {@code caseSensitive}
     * @see ResultSetMetaData#isCaseSensitive(int)
     */
    void setCaseSensitive(final boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    // -------------------------------------------------------------------------------------------------------- currency

    /**
     * Returns the value of {@code currency}.
     *
     * @return the value of {@code currency}
     * @see ResultSetMetaData#isCurrency(int)
     */
    public boolean isCurrency() {
        return currency;
    }

    /**
     * Sets the value of {@code currency}.
     *
     * @param currency the new value of {@code currency}
     * @see ResultSetMetaData#isCurrency(int)
     */
    void setCurrency(final boolean currency) {
        this.currency = currency;
    }

    // ---------------------------------------------------------------------------------------------- definitelyWritable

    /**
     * Returns the value of {@code definitelyWritable}.
     *
     * @return the value of {@code definitelyWritable}.
     * @see ResultSetMetaData#isDefinitelyWritable(int)
     */
    public boolean isDefinitelyWritable() {
        return definitelyWritable;
    }

    /**
     * Sets the value of {@code definitelyWritable}.
     *
     * @param definitelyWritable new value for {@code definitelyWritable}.
     * @see ResultSetMetaData#isDefinitelyWritable(int)
     */
    void setDefinitelyWritable(final boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    // -------------------------------------------------------------------------------------------------------- nullable

    /**
     * Returns the value of {@code nullable}.
     *
     * @return the value of {@code nullable}.
     * @see ResultSetMetaData#isNullable(int)
     */
    public int getNullable() {
        return nullable;
    }

    /**
     * Sets the value of {@code nullable}.
     *
     * @param nullable new value for {@code nullable}
     * @see ResultSetMetaData#isNullable(int)
     */
    void setNullable(final int nullable) {
        this.nullable = nullable;
    }

    // -------------------------------------------------------------------------------------------------------- readOnly

    /**
     * Returns the value of {@code readOnly}.
     *
     * @return the value of {@code readOnly}.
     * @see ResultSetMetaData#isReadOnly(int)
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets the value of {@code readOnly}.
     *
     * @param readOnly new value for {@code readOnly}.
     * @see ResultSetMetaData#isReadOnly(int)
     */
    void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    // ------------------------------------------------------------------------------------------------------ searchable

    /**
     * Returns the value of {@code searchable}.
     *
     * @return the value of {@code searchable}
     * @see ResultSetMetaData#isSearchable(int)
     */
    public boolean isSearchable() {
        return searchable;
    }

    /**
     * Sets the value of {@code searchable}.
     *
     * @param searchable the new value of {@code searchable}
     * @see ResultSetMetaData#isSearchable(int)
     */
    void setSearchable(final boolean searchable) {
        this.searchable = searchable;
    }

    // ---------------------------------------------------------------------------------------------------------- signed

    /**
     * Returns the value of {@code signed}.
     *
     * @return the value of {@code signed}
     * @see ResultSetMetaData#isSigned(int)
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * Sets the value of {@code signed}.
     *
     * @param signed new value for {@code signed}.
     * @see ResultSetMetaData#isSigned(int)
     */
    void setSigned(final boolean signed) {
        this.signed = signed;
    }

    // -------------------------------------------------------------------------------------------------------- writable

    /**
     * Returns the value of {@code writable}.
     *
     * @return the value of {@code writable}
     * @see ResultSetMetaData#isWritable(int)
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * Sets the value of {@code writable}.
     *
     * @param writable the new value of {@code writable}
     * @see ResultSetMetaData#isWritable(int)
     */
    void setWritable(final boolean writable) {
        this.writable = writable;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @XmlElement(required = true)
    private String catalogName;

    @XmlElement(required = true)
    private String columnClassName;

    @XmlElement(required = true)
    private int columnDisplaySize;

    @XmlElement(required = true)
    private String columnLabel;

    @XmlElement(required = true)
    private String columnName;

    @XmlElement(required = true)
    private int columnType;

    @XmlElement(required = true)
    private String columnTypeName;

    @XmlElement(required = true)
    private int precision;

    @XmlElement(required = true)
    private int scale;

    @XmlElement(required = true)
    private String schemaName;

    @XmlElement(required = true)
    private String tableName;

    @XmlElement(required = true)
    private boolean autoIncrement;

    @XmlElement(required = true)
    private boolean caseSensitive;

    @XmlElement(required = true)
    private boolean currency;

    @XmlElement(required = true)
    private boolean definitelyWritable;

    @XmlElement(required = true)
    private int nullable;

    @XmlElement(required = true)
    private boolean readOnly;

    @XmlElement(required = true)
    private boolean searchable;

    @XmlElement(required = true)
    private boolean signed;

    @XmlElement(required = true)
    private boolean writable;
}
