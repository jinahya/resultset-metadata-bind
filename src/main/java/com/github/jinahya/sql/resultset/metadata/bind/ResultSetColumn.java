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

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ResultSetColumn {

    // ------------------------------------------------------------- catalogName
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(final String catalogName) {
        this.catalogName = catalogName;
    }

    public String catalogName() {
        return getCatalogName();
    }

    public ResultSetColumn catalogName(final String catalogName) {
        setCatalogName(catalogName);
        return this;
    }

    // --------------------------------------------------------- columnClassName
    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(final String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public String columnClassName() {
        return getColumnClassName();
    }

    public ResultSetColumn columnClassName(final String columnClassName) {
        setColumnClassName(columnClassName);
        return this;
    }

    // ------------------------------------------------------- columnDisplaySize
    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(final int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public int columnDisplaySize() {
        return getColumnDisplaySize();
    }

    public ResultSetColumn columnDisplaySize(final int columnDisplaySize) {
        setColumnDisplaySize(columnDisplaySize);
        return this;
    }

    // ------------------------------------------------------------- columnLebel
    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(final String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String columnLabel() {
        return getColumnLabel();
    }

    public ResultSetColumn columnLabel(final String columnLabel) {
        setColumnLabel(columnLabel);
        return this;
    }

    // -------------------------------------------------------------- columnName
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public String columnName() {
        return getColumnName();
    }

    public ResultSetColumn columnName(final String columnName) {
        setColumnName(columnName);
        return this;
    }

    // -------------------------------------------------------------- columnType
    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(final int columnType) {
        this.columnType = columnType;
    }

    public int columnType() {
        return getColumnType();
    }

    public ResultSetColumn columnType(final int columnType) {
        setColumnType(columnType);
        return this;
    }

    // ---------------------------------------------------------- columnTypeName
    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(final String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String columnTypeName() {
        return getColumnTypeName();
    }

    public ResultSetColumn columnTypeName(final String columnTypeName) {
        setColumnTypeName(columnTypeName);
        return this;
    }

    // --------------------------------------------------------------- precision
    public int getPrecision() {
        return precision;
    }

    public void setPrecision(final int precision) {
        this.precision = precision;
    }

    public int precision() {
        return getPrecision();
    }

    public ResultSetColumn precision(final int precision) {
        setPrecision(precision);
        return this;
    }

    // ------------------------------------------------------------------- scale
    public int getScale() {
        return scale;
    }

    public void setScale(final int scale) {
        this.scale = scale;
    }

    public int scale() {
        return getScale();
    }

    public ResultSetColumn scale(final int scale) {
        setScale(scale);
        return this;
    }

    // -------------------------------------------------------------- schemaName
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    public String schemaName() {
        return getSchemaName();
    }

    public ResultSetColumn schemaName(final String schemaName) {
        setSchemaName(schemaName);
        return this;
    }

    // --------------------------------------------------------------- tableName
    public String getTableName() {
        return tableName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public String tableName() {
        return getTableName();
    }

    public ResultSetColumn tableName(final String tableName) {
        setTableName(tableName);
        return this;
    }

    // ----------------------------------------------------------- autoIncrement
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(final boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean autoIncrement() {
        return isAutoIncrement();
    }

    public ResultSetColumn autoIncrement(final boolean autoIncrement) {
        setAutoIncrement(autoIncrement);
        return this;
    }

    // ----------------------------------------------------------- caseSensitive
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(final boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean caseSensitive() {
        return isCaseSensitive();
    }

    public ResultSetColumn caseSensitive(final boolean caseSensitive) {
        setCaseSensitive(caseSensitive);
        return this;
    }

    // ---------------------------------------------------------------- currency
    public boolean isCurrency() {
        return currency;
    }

    public void setCurrency(final boolean currency) {
        this.currency = currency;
    }

    public boolean currency() {
        return isCurrency();
    }

    public ResultSetColumn currency(final boolean currency) {
        setCurrency(currency);
        return this;
    }

    // ------------------------------------------------------ definitelyWritable
    public boolean isDefinitelyWritable() {
        return definitelyWritable;
    }

    public void setDefinitelyWritable(final boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    public boolean definitelyWritable() {
        return isDefinitelyWritable();
    }

    public ResultSetColumn definitelyWritable(
            final boolean definitelyWritable) {
        setDefinitelyWritable(definitelyWritable);
        return this;
    }

    // ---------------------------------------------------------------- nullable
    public int getNullable() {
        return nullable;
    }

    public void setNullable(final int nullable) {
        this.nullable = nullable;
    }

    public int nullable() {
        return getNullable();
    }

    public ResultSetColumn nullable(final int nullable) {
        setNullable(nullable);
        return this;
    }

    // ---------------------------------------------------------------- readOnly
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean readOnly() {
        return isReadOnly();
    }

    public ResultSetColumn readOnly(final boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    // -------------------------------------------------------------- searchable
    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(final boolean searchable) {
        this.searchable = searchable;
    }

    public boolean searchable() {
        return isSearchable();
    }

    public ResultSetColumn searchable(final boolean searchable) {
        setSearchable(searchable);
        return this;
    }

    // ------------------------------------------------------------------ signed
    public boolean isSigned() {
        return signed;
    }

    public void setSigned(final boolean signed) {
        this.signed = signed;
    }

    public boolean signed() {
        return isSigned();
    }

    public ResultSetColumn signed(final boolean signed) {
        setSigned(signed);
        return this;
    }

    // ---------------------------------------------------------------- writable
    public boolean isWritable() {
        return writable;
    }

    public void setWritable(final boolean writable) {
        this.writable = writable;
    }

    public boolean writable() {
        return isWritable();
    }

    public ResultSetColumn writable(final boolean writable) {
        setWritable(writable);
        return this;
    }

    // -------------------------------------------------------------------------
    private String catalogName;

    private String columnClassName;

    private int columnDisplaySize;

    private String columnLabel;

    private String columnName;

    private int columnType;

    private String columnTypeName;

    private int precision;

    private int scale;

    private String schemaName;

    private String tableName;

    private boolean autoIncrement;

    private boolean caseSensitive;

    private boolean currency;

    private boolean definitelyWritable;

    private int nullable;

    private boolean readOnly;

    private boolean searchable;

    private boolean signed;

    private boolean writable;
}
