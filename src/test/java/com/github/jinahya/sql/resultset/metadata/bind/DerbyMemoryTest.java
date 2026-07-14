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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

/**
 * Tests result-set metadata against an in-memory Apache Derby database.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DerbyMemoryTest
        extends Memory_$_Test {

    private static final String CONNECTION_URL = "jdbc:derby:memory:test";

    /**
     * Creates the in-memory database before the inherited tests run.
     *
     * @throws SQLException if the database cannot be created
     */
    @BeforeAll
    public void createDatabase() throws SQLException {
        final var properties = new Properties();
        properties.put("create", "true");
        try (var ignored = getConnection(CONNECTION_URL, properties)) {
            // creates the in-memory database
        }
    }

    /**
     * Shuts down the in-memory database after the inherited tests finish.
     */
    @AfterAll
    public void shutdownDatabase() {
        final var properties = new Properties();
        properties.put("shutdown", "true");
        try (var ignored = getConnection(CONNECTION_URL, properties)) {
            // Derby reports successful shutdown by throwing SQLException.
        } catch (final SQLException expected) {
            // expected
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Connection connect() throws SQLException {
        return getConnection(CONNECTION_URL);
    }
}
