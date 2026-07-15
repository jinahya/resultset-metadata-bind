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

import io.vavr.CheckedFunction1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import static java.util.Objects.requireNonNull;

/**
 * An abstract test class for in-memory databases.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
abstract class Memory_$_Test {

    private static final boolean PRINT_WRITTEN_FILES = false;

    @TempDir
    static Path tempDir;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Opens a connection to the in-memory database under test.
     *
     * @return a new database connection
     * @throws SQLException if the connection cannot be opened
     */
    abstract Connection connect() throws SQLException;

    /**
     * Applies the supplied function to a newly opened connection and closes the connection afterward.
     *
     * @param function the function to apply
     * @param <R>      the function's result type
     * @return the function result
     * @throws Throwable if opening the connection, applying the function, or closing the connection fails
     */
    <R> R applyConnection(final CheckedFunction1<? super Connection, ? extends R> function) throws Throwable {
        requireNonNull(function, "function is null");
        try (var connection = connect()) {
            return function.apply(connection);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Binds and marshals metadata for every column in the supplied result set.
     *
     * @param results the result set whose column metadata is tested
     * @throws Exception if metadata cannot be read, marshalled, or unmarshalled
     */
    private void bind(final ResultSet results) throws Exception {
        final var columns = ResultSetMetaDataColumn.bind(results);
        {
            final var xml = Files.createTempFile(tempDir, "result-set-metadata-columns-", ".xml");
            final var context = jakarta.xml.bind.JAXBContext.newInstance(getClass().getPackageName());
            context.createMarshaller().marshal(ResultSetMetaDataColumnWrapper.of(columns), xml.toFile());
            if (PRINT_WRITTEN_FILES) {
                System.out.println(Files.readString(xml));
            }
            context.createUnmarshaller().unmarshal(xml.toFile());
        }
        {
            final var json = Files.createTempFile(tempDir, "result-set-metadata-columns-", ".json");
            JakartaJsonBindingTestUtils.write(columns, json);
            if (PRINT_WRITTEN_FILES) {
                System.out.println(Files.readString(json));
            }
            JakartaJsonBindingTestUtils.read(json, java.util.List.class);
        }
        for (final var column : columns) {
            log.debug("\ncolumn: {}\n", column);
            {
                final var xml = Files.createTempFile(tempDir, "result-set-metadata-column-", ".xml");
                JakartaXmlBindingTestUtils.marshal(column, xml);
                if (PRINT_WRITTEN_FILES) {
                    System.out.println(Files.readString(xml));
                }
                JakartaXmlBindingTestUtils.unmarshal(xml, ResultSetMetaDataColumn.class);
            }
            {
                final var json = Files.createTempFile(tempDir, "result-set-metadata-column-", ".json");
                JakartaJsonBindingTestUtils.write(column, json);
                if (PRINT_WRITTEN_FILES) {
                    System.out.println(Files.readString(json));
                }
                JakartaJsonBindingTestUtils.read(json, ResultSetMetaDataColumn.class);
            }
        }
    }

    /**
     * Binds and marshals column metadata for the result set opened by the specified function against a newly opened
     * connection. The test is skipped when the driver does not support the metadata method.
     *
     * @param function the function opening the result set to test
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    private void bind(final CheckedFunction1<? super DatabaseMetaData, ? extends ResultSet> function) throws Throwable {
        requireNonNull(function, "function is null");
        applyConnection(c -> {
            final var metadata = c.getMetaData();
            try (var results = function.apply(metadata)) {
                bind(results);
            } catch (final SQLFeatureNotSupportedException sqlfnse) {
                log.debug("unsupported by the driver; {}", sqlfnse.getMessage());
            }
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getCatalogs()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getCatalogs__() throws Throwable {
        bind(DatabaseMetaData::getCatalogs);
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getSchemas()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getSchemas__() throws Throwable {
        bind(DatabaseMetaData::getSchemas);
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getSchemas(String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getSchemas_catalog_schemaPattern() throws Throwable {
        bind(dmd -> dmd.getSchemas(null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getTables(String, String, String, String[])}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getTables__() throws Throwable {
        bind(dmd -> dmd.getTables(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getTableTypes()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getTableTypes__() throws Throwable {
        bind(DatabaseMetaData::getTableTypes);
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getTypeInfo()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getTypeInfo__() throws Throwable {
        bind(DatabaseMetaData::getTypeInfo);
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getColumns(String, String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getColumns__() throws Throwable {
        bind(dmd -> dmd.getColumns(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getProcedures(String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getProcedures__() throws Throwable {
        bind(dmd -> dmd.getProcedures(null, null, null));
    }

    /**
     * Tests column metadata returned by
     * {@link DatabaseMetaData#getProcedureColumns(String, String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getProcedureColumns__() throws Throwable {
        bind(dmd -> dmd.getProcedureColumns(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getFunctions(String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getFunctions__() throws Throwable {
        bind(dmd -> dmd.getFunctions(null, null, null));
    }

    /**
     * Tests column metadata returned by
     * {@link DatabaseMetaData#getFunctionColumns(String, String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getFunctionColumns__() throws Throwable {
        bind(dmd -> dmd.getFunctionColumns(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getTablePrivileges(String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getTablePrivileges__() throws Throwable {
        bind(dmd -> dmd.getTablePrivileges(null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getUDTs(String, String, String, int[])}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getUDTs__() throws Throwable {
        bind(dmd -> dmd.getUDTs(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getAttributes(String, String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getAttributes__() throws Throwable {
        bind(dmd -> dmd.getAttributes(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getSuperTypes(String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getSuperTypes__() throws Throwable {
        bind(dmd -> dmd.getSuperTypes(null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getSuperTables(String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getSuperTables__() throws Throwable {
        bind(dmd -> dmd.getSuperTables(null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getPseudoColumns(String, String, String, String)}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getPseudoColumns__() throws Throwable {
        bind(dmd -> dmd.getPseudoColumns(null, null, null, null));
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getClientInfoProperties()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getClientInfoProperties__() throws Throwable {
        bind(DatabaseMetaData::getClientInfoProperties);
    }
}
