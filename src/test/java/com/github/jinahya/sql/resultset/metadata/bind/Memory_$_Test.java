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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getCatalogs()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getCatalogs__() throws Throwable {
        applyConnection(c -> {
            try (var results = c.getMetaData().getCatalogs()) {
                bind(results);
            }
            return null;
        });
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getSchemas()}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void getSchemas__() throws Throwable {
        applyConnection(c -> {
            try (var results = c.getMetaData().getSchemas()) {
                bind(results);
            }
            return null;
        });
    }

    /**
     * Tests column metadata returned by {@link DatabaseMetaData#getTables(String, String, String, String[])}.
     *
     * @throws Throwable if connecting, reading metadata, or marshalling fails
     */
    @Test
    public void tables() throws Throwable {
        applyConnection(connection -> {
            final var metadata = connection.getMetaData();
            try (var results = metadata.getTables(null, null, null, null)) {
                bind(results);
            }
            return null;
        });
    }
}
