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
//@lombok.Data
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

    public void setScale(int scale) {
        this.scale = scale;
    }

    // -------------------------------------------------------------- schemaName
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    // --------------------------------------------------------------- tableName
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean isCurrency() {
        return currency;
    }

    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    public boolean isDefinitelyWritable() {
        return definitelyWritable;
    }

    public void setDefinitelyWritable(boolean definitelyWritable) {
        this.definitelyWritable = definitelyWritable;
    }

    public int getNullable() {
        return nullable;
    }

    public void setNullable(int nullable) {
        this.nullable = nullable;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

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
