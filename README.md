# resultset-metadata-bind

[![Java CI with Maven](https://github.com/jinahya/resultset-metadata-bind/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/resultset-metadata-bind/actions/workflows/maven.yml)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_resultset-metadata-bind&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_resultset-metadata-bind)

[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.jinahya/resultset-metadata-bind)](https://central.sonatype.com/artifact/io.github.jinahya/resultset-metadata-bind)
[![javadoc](https://javadoc.io/badge2/io.github.jinahya/resultset-metadata-bind/javadoc.svg)](https://javadoc.io/doc/io.github.jinahya/resultset-metadata-bind)

A tiny Java library that binds [`java.sql.ResultSetMetaData`](https://docs.oracle.com/en/java/javase/17/docs/api/java.sql/java/sql/ResultSetMetaData.html)
into plain Java objects you can inspect, serialize, and pass around.

## Overview

`ResultSetMetaData` exposes column information one accessor and one column index at a time
(`getColumnName(int)`, `getColumnType(int)`, `isNullable(int)`, …), and every accessor throws
`SQLException`. This library reads all of that in one pass into a simple bean —
[`ResultSetMetaDataColumn`](src/main/java/com/github/jinahya/sql/resultset/metadata/bind/ResultSetMetaDataColumn.java) —
so the metadata becomes an ordinary object graph that is easy to iterate, log, and marshal to XML or JSON.

Each `ResultSetMetaDataColumn` mirrors the per-column accessors of `ResultSetMetaData`:

| Property             | Source accessor                       |
|----------------------|---------------------------------------|
| `catalogName`        | `getCatalogName(int)`                 |
| `columnClassName`    | `getColumnClassName(int)`             |
| `columnDisplaySize`  | `getColumnDisplaySize(int)`           |
| `columnLabel`        | `getColumnLabel(int)`                 |
| `columnName`         | `getColumnName(int)`                  |
| `columnType`         | `getColumnType(int)`                  |
| `columnTypeName`     | `getColumnTypeName(int)`              |
| `precision`          | `getPrecision(int)`                   |
| `scale`              | `getScale(int)`                       |
| `schemaName`         | `getSchemaName(int)`                  |
| `tableName`          | `getTableName(int)`                   |
| `autoIncrement`      | `isAutoIncrement(int)`                |
| `caseSensitive`      | `isCaseSensitive(int)`                |
| `currency`           | `isCurrency(int)`                     |
| `definitelyWritable` | `isDefinitelyWritable(int)`           |
| `nullable`           | `isNullable(int)`                     |
| `readOnly`           | `isReadOnly(int)`                     |
| `searchable`         | `isSearchable(int)`                   |
| `signed`             | `isSigned(int)`                       |
| `writable`           | `isWritable(int)`                     |

## Requirements

- **Runtime:** Java 17 or later.
- Jakarta XML Binding, Jakarta JSON Binding, and Jakarta Validation APIs are declared as
  `provided`/`optional` dependencies — bring your own API and implementation only for the
  binding features you actually use (see [Serialization](#serialization)).

## Installation

The artifact is published to [Maven Central](https://central.sonatype.com/artifact/io.github.jinahya/resultset-metadata-bind).
For the latest released version, see the Maven Central badge above.

### Maven

```xml
<dependency>
    <groupId>io.github.jinahya</groupId>
    <artifactId>resultset-metadata-bind</artifactId>
    <version>LATEST_VERSION</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.jinahya:resultset-metadata-bind:LATEST_VERSION'
```

On the module path, the automatic module name is
`com.github.jinahya.sql.resultset.metadata.bind`.

## Usage

All binding is done through the static `bind` methods on `ResultSetMetaDataColumn`.

```java
import com.github.jinahya.sql.resultset.metadata.bind.ResultSetMetaDataColumn;

try (var connection = dataSource.getConnection();
     var statement = connection.prepareStatement("SELECT * FROM some_table");
     var results = statement.executeQuery()) {

    // Bind every column of a ResultSet into a List.
    List<ResultSetMetaDataColumn> columns = ResultSetMetaDataColumn.bind(results);

    for (var column : columns) {
        System.out.printf("%-20s %s%n", column.getColumnName(), column.getColumnTypeName());
    }
}
```

Other entry points:

```java
// From a ResultSetMetaData instead of a ResultSet.
List<ResultSetMetaDataColumn> columns = ResultSetMetaDataColumn.bind(metadata);

// Stream each column to a consumer, in column-index order.
ResultSetMetaDataColumn.bind(results, column -> { /* ... */ });

// Bind a single, one-based column index.
ResultSetMetaDataColumn column = ResultSetMetaDataColumn.bind(metadata, 1);
```

Every `bind` overload propagates `SQLException` from the underlying accessors.

## Serialization

The binding types are annotated for the Jakarta binding stacks so that the objects can be
marshalled without extra configuration.

### JSON — Jakarta JSON Binding (JSON-B)

`ResultSetMetaDataColumn` binds directly to its fields, so an ordinary `Jsonb` works for both
serialization and deserialization:

```java
try (var jsonb = JsonbBuilder.create()) {
    String json = jsonb.toJson(columns);
}
```

### XML — Jakarta XML Binding (JAXB)

Individual columns are `@XmlRootElement`s. Because JAXB cannot marshal a bare `List` as a
document, the package also provides
[`ResultSetMetaDataColumnWrapper`](src/main/java/com/github/jinahya/sql/resultset/metadata/bind/ResultSetMetaDataColumnWrapper.java),
which supplies a `resultSetMetaDataColumns` root element wrapping `resultSetMetaDataColumn`
children. Create a `JAXBContext` for the package and marshal as usual:

```java
var context = JAXBContext.newInstance(ResultSetMetaDataColumn.class.getPackageName());
context.createMarshaller().marshal(wrapper, System.out);
```

Marshalled XML uses the namespace `https://github.com/jinahya/resultset-metadata-bind`
(prefix `rsmdb`).

## Building

Building and testing the project requires **JDK 25** and Maven 3.6.3+ (production classes are
still compiled for Java 17). The test suite exercises the bindings against several embedded
JDBC drivers — Derby, H2, HSQLDB, SQLite, and DuckDB.

```bash
mvn clean verify
```

## License

Licensed under the [Apache License, Version 2.0](LICENSE).

Copyright © 2016 [Jinahya, Inc.](https://jinahya.com)
