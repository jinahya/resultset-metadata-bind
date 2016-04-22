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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class binds column information from {@link ResultSetMetaData}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlRootElement
public class ResultSetMetaDataColumn {

    /**
     * Binds a column information from specified ResultSetMetaData.
     *
     * @param metadata the ResultSetMetaData
     * @param column the column to bind
     * @return a bound column information
     * @throws SQLException if a database access error occurs.
     */
    public static ResultSetMetaDataColumn bind(final ResultSetMetaData metadata,
                                               final int column)
            throws SQLException {
        return new ResultSetMetaDataColumn()
                .catalogName(metadata.getCatalogName(column))
                .columnClassName(metadata.getColumnClassName(column))
                .columnDisplaySize(metadata.getColumnDisplaySize(column))
                .columnLabel(metadata.getColumnLabel(column))
                .columnName(metadata.getColumnName(column))
                .columnType(metadata.getColumnType(column))
                .columnTypeName(metadata.getColumnTypeName(column))
                .precision(metadata.getPrecision(column))
                .scale(metadata.getScale(column))
                .schemaName(metadata.getSchemaName(column))
                .tableName(metadata.getTableName(column))
                .autoIncrement(metadata.isAutoIncrement(column))
                .caseSensitive(metadata.isCaseSensitive(column))
                .currency(metadata.isCurrency(column))
                .definitelyWritable(metadata.isDefinitelyWritable(column))
                .nullable(metadata.isNullable(column))
                .readOnly(metadata.isReadOnly(column))
                .searchable(metadata.isSearchable(column))
                .signed(metadata.isSigned(column))
                .writable(metadata.isWritable(column));
    }

    /**
     * Binds all column information from given ResultSetMetaData.
     *
     * @param metadata the ResultSetMetaData
     * @return a list of bound column formation.
     * @throws SQLException if a database access error occurs.
     */
    public static List<ResultSetMetaDataColumn> bind(
            final ResultSetMetaData metadata)
            throws SQLException {
        final int columnCount = metadata.getColumnCount();
        final List<ResultSetMetaDataColumn> bound
                = new ArrayList<ResultSetMetaDataColumn>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            bound.add(bind(metadata, i));
        }
        return bound;
    }

    /**
     * Binds all column information from given ResultSet.
     *
     * @param results the ResultSet
     * @return a list of bound column information
     * @throws SQLException if a database access error occurs.
     */
    public static List<ResultSetMetaDataColumn> bind(final ResultSet results)
            throws SQLException {
        return bind(results.getMetaData());
    }

    @Override
    public String toString() {
        return super.toString()
               + "?catalogName=" + catalogName
               + "&columnClassName=" + columnClassName
               + "&columnDisplaySize=" + columnDisplaySize
               + "&columnLabel=" + columnLabel
               + "&columnName=" + columnName
               + "&columnType=" + columnType
               + "&columnTypeName=" + columnTypeName
               + "&precision=" + precision
               + "&scale=" + scale
               + "&schemaName=" + schemaName
               + "&tableName=" + tableName
               + "&autoIncrement=" + autoIncrement
               + "&caseSensigive=" + caseSensitive
               + "&currency=" + currency
               + "&definetelyWritable=" + definitelyWritable
               + "&nullable=" + nullable
               + "&readOnly=" + readOnly
               + "&searchable=" + searchable
               + "&signed=" + signed
               + "&writable=" + writable;
    }

    // ------------------------------------------------------------- catalogName
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
     * Replaces the value of {@code catalogName} with given.
     *
     * @param catalogName new value for {@code catalogName}
     * @see ResultSetMetaData#getCatalogName(int)
     */
    public void setCatalogName(final String catalogName) {
        this.catalogName = catalogName;
    }

    public String catalogName() {
        return getCatalogName();
    }

    public ResultSetMetaDataColumn catalogName(final String catalogName) {
        setCatalogName(catalogName);
        return this;
    }

    // --------------------------------------------------------- columnClassName
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
     * Replaces the value of {@code columnClassName} with given.
     *
     * @param columnClassName new value for {@code columnClassName}.
     * @see ResultSetMetaData#getColumnClassName(int)
     */
    public void setColumnClassName(final String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public String columnClassName() {
        return getColumnClassName();
    }

    public ResultSetMetaDataColumn columnClassName(final String columnClassName) {
        setColumnClassName(columnClassName);
        return this;
    }

    // ------------------------------------------------------- columnDisplaySize
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
     * Replaces the value of {@code columnDisplaySize} with given.
     *
     * @param columnDisplaySize new value for {@code columnDisplaySize}.
     * @see ResultSetMetaData#getColumnDisplaySize(int)
     */
    public void setColumnDisplaySize(final int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public int columnDisplaySize() {
        return getColumnDisplaySize();
    }

    public ResultSetMetaDataColumn columnDisplaySize(final int columnDisplaySize) {
        setColumnDisplaySize(columnDisplaySize);
        return this;
    }

    // ------------------------------------------------------------- columnLebel
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
     * Replaces the value of {@code columnLabel} with given.
     *
     * @param columnLabel new value for {@code columnLabel}
     * @see ResultSetMetaData#getColumnLabel(int)
     */
    public void setColumnLabel(final String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String columnLabel() {
        return getColumnLabel();
    }

    public ResultSetMetaDataColumn columnLabel(final String columnLabel) {
        setColumnLabel(columnLabel);
        return this;
    }

    // -------------------------------------------------------------- columnName
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
     * Replaces the value of {@code columnName}.
     *
     * @param columnName the value of {@code columnName}.
     * @see ResultSetMetaData#getColumnName(int)
     */
    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public String columnName() {
        return getColumnName();
    }

    public ResultSetMetaDataColumn columnName(final String columnName) {
        setColumnName(columnName);
        return this;
    }

    // -------------------------------------------------------------- columnType
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
     * Replaces the value of {@code columnType}
     *
     * @param columnType new value for {@code columnType}
     * @see ResultSetMetaData#getColumnType(int)
     */
    public void setColumnType(final int columnType) {
        this.columnType = columnType;
    }

    public int columnType() {
        return getColumnType();
    }

    public ResultSetMetaDataColumn columnType(final int columnType) {
        setColumnType(columnType);
        return this;
    }

    // ---------------------------------------------------------- columnTypeName
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
     * Replaces the value of {@code columnTypeName}.
     *
     * @param columnTypeName new value for {@code columnTypeName}
     * @see ResultSetMetaData#getColumnTypeName(int)
     */
    public void setColumnTypeName(final String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String columnTypeName() {
        return getColumnTypeName();
    }

    public ResultSetMetaDataColumn columnTypeName(final String columnTypeName) {
        setColumnTypeName(columnTypeName);
        return this;
    }

    // --------------------------------------------------------------- precision
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
     * Replaces the value of {@code precision} with given.
     *
     * @param precision new value for {@code precision}
     * @see ResultSetMetaData#getPrecision(int)
     */
    public void setPrecision(final int precision) {
        this.precision = precision;
    }

    public int precision() {
        return getPrecision();
    }

    public ResultSetMetaDataColumn precision(final int precision) {
        setPrecision(precision);
        return this;
    }

    // ------------------------------------------------------------------- scale
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
     * Replaces the value of {@code scale} with given.
     *
     * @param scale new value for {@code scale}.
     * @see ResultSetMetaData#getScale(int)
     */
    public void setScale(final int scale) {
        this.scale = scale;
    }

    public int scale() {
        return getScale();
    }

    public ResultSetMetaDataColumn scale(final int scale) {
        setScale(scale);
        return this;
    }

    // -------------------------------------------------------------- schemaName
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
     * Replaces the value of {@code schemaName} with given.
     *
     * @param schemaName new value for {@code schemaName}.
     * @see ResultSetMetaData#getSchemaName(int)
     */
    public void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    public String schemaName() {
        return getSchemaName();
    }

    public ResultSetMetaDataColumn schemaName(final String schemaName) {
        setSchemaName(schemaName);
        return this;
    }

    // --------------------------------------------------------------- tableName
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
     * Replaces the value of {@code tableName} with given.
     *
     * @param tableName new value for {@code tableName}
     * @see ResultSetMetaData#getTableName(int)
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public String tableName() {
        return getTableName();
    }

    public ResultSetMetaDataColumn tableName(final String tableName) {
        setTableName(tableName);
        return this;
    }

    // ----------------------------------------------------------- autoIncrement
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
     * Replaces the value of {@code autoIncrement} field with given.
     *
     * @param autoIncrement new value for {@code autoIncrement} field.
     * @see ResultSetMetaData#isAutoIncrement(int)
     */
    public void setAutoIncrement(final boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean autoIncrement() {
        return isAutoIncrement();
    }

    public ResultSetMetaDataColumn autoIncrement(final boolean autoIncrement) {
        setAutoIncrement(autoIncrement);
        return this;
    }

    // ----------------------------------------------------------- caseSensitive
    /**
     * Returns the value of {@code caseSensitive} field.
     *
     * @return the value of {@code caseSensitive} field.
     * @see ResultSetMetaData#isCaseSensitive(int)
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Replaces the value of {@code caseSensitive} field with given.
     *
     * @param caseSensitive new value for {@code caseSensitive} field.
     * @see ResultSetMetaData#isCaseSensitive(int)
     */
    public void setCaseSensitive(final boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean caseSensitive() {
        return isCaseSensitive();
    }

    public ResultSetMetaDataColumn caseSensitive(final boolean caseSensitive) {
        setCaseSensitive(caseSensitive);
        return this;
    }

    // ---------------------------------------------------------------- currency
    /**
     * Returns the value of {@code currency} field.
     *
     * @return the value of {@code currency} field.
     * @see ResultSetMetaData#isCurrency(int)
     */
    public boolean isCurrency() {
        return currency;
    }

    /**
     * Replaces the value of {@code currency} field with given.
     *
     * @param currency new value for {@code currency} field.
     * @see ResultSetMetaData#isCurrency(int)
     */
    public void setCurrency(final boolean currency) {
        this.currency = currency;
    }

    public boolean currency() {
        return isCurrency();
    }

    public ResultSetMetaDataColumn currency(final boolean currency) {
        setCurrency(currency);
        return this;
    }

    // ------------------------------------------------------ definitelyWritable
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
     * Replaces the value of {@code definitelyWritable} with given.
     *
     * @param definitelyWritable new value for {@code definitelyWritable}.
     * @see ResultSetMetaData#isDefinitelyWritable(int)
     */
    public void setDefinitelyWritable(final boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    public boolean definitelyWritable() {
        return isDefinitelyWritable();
    }

    public ResultSetMetaDataColumn definitelyWritable(
            final boolean definitelyWritable) {
        setDefinitelyWritable(definitelyWritable);
        return this;
    }

    // ---------------------------------------------------------------- nullable
    /**
     * Returns the value of {@code nullable}
     *
     * @return the value of {@code nullable}.
     * @see ResultSetMetaData#isNullable(int) 
     */
    public int getNullable() {
        return nullable;
    }

    /**
     * Replaces the value of {@code nullable} with given.
     *
     * @param nullable new value for {@code nullable}
     * @see ResultSetMetaData#isNullable(int)
     */
    public void setNullable(final int nullable) {
        this.nullable = nullable;
    }

    public int nullable() {
        return getNullable();
    }

    public ResultSetMetaDataColumn nullable(final int nullable) {
        setNullable(nullable);
        return this;
    }

    // ---------------------------------------------------------------- readOnly
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
     * Replaces the value of {@code readOnly}.
     *
     * @param readOnly new value for {@code readOnly}.
     * @see ResultSetMetaData#isReadOnly(int)
     */
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean readOnly() {
        return isReadOnly();
    }

    public ResultSetMetaDataColumn readOnly(final boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    // -------------------------------------------------------------- searchable
    /**
     * Returns the value of {@code searchable} field.
     *
     * @return the value of {@code searchable} field.
     * @see ResultSetMetaData#isSearchable(int)
     */
    public boolean isSearchable() {
        return searchable;
    }

    /**
     * Replaces the value of {@code searchable} field with given.
     *
     * @param searchable the value of {@code searchable} field.
     * @see ResultSetMetaData#isSearchable(int)
     */
    public void setSearchable(final boolean searchable) {
        this.searchable = searchable;
    }

    public boolean searchable() {
        return isSearchable();
    }

    public ResultSetMetaDataColumn searchable(final boolean searchable) {
        setSearchable(searchable);
        return this;
    }

    // ------------------------------------------------------------------ signed
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
     * Replaces the value of {@code signed} with given.
     *
     * @param signed new value for {@code signed}.
     * @see ResultSetMetaData#isSigned(int)
     */
    public void setSigned(final boolean signed) {
        this.signed = signed;
    }

    public boolean signed() {
        return isSigned();
    }

    public ResultSetMetaDataColumn signed(final boolean signed) {
        setSigned(signed);
        return this;
    }

    // ---------------------------------------------------------------- writable
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
     * Replaces the value of {@code writable} with given.
     *
     * @param writable new value of {@code writable}
     * @see ResultSetMetaData#isWritable(int)
     */
    public void setWritable(final boolean writable) {
        this.writable = writable;
    }

    public boolean writable() {
        return isWritable();
    }

    public ResultSetMetaDataColumn writable(final boolean writable) {
        setWritable(writable);
        return this;
    }

    // -------------------------------------------------------------------------
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
