/*
 * Copyright 2013 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.util.List;
import javax.xml.bind.JAXBException;
import static org.slf4j.LoggerFactory.getLogger;
import static java.sql.DriverManager.getConnection;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class DerbyMemoryTest {

    private static final Logger logger = getLogger(DerbyMemoryTest.class);

    private static final String DRIVER_NAME
            = "org.apache.derby.jdbc.EmbeddedDriver";

    private static final String CONNECTION_URL = "jdbc:derby:memory:test";

    @BeforeClass
    private static void beforeClass() throws SQLException {
        final Properties properties = new Properties();
        properties.put("create", "true");
        try (Connection connection
                = getConnection(CONNECTION_URL, properties)) {
        }
    }

    @AfterClass
    private static void afterClass() {
        final Properties properties = new Properties();
        properties.put("shutdown", "true");
        try {
            try (Connection connection
                    = getConnection(CONNECTION_URL, properties)) {
            }
        } catch (final SQLException sqle) {
            // this is expected
            // Shutdown commands always raise SQLExceptions.
        }
    }

    @Test
    public void catalogs() throws SQLException, JAXBException {
        try (Connection connection = getConnection(CONNECTION_URL)) {
            final DatabaseMetaData database = connection.getMetaData();
            try (ResultSet results = database.getCatalogs()) {
                final List<ResultSetMetaDataColumn> columns
                        = ResultSetMetaDataColumn.bind(results);
                for (final ResultSetMetaDataColumn column : columns) {
                    logger.debug("DatabaseMetaData.catalog: {}", column);
                    JaxbTest.marshalInstance(column);
                }
            }
        }
    }

    @Test
    public void tables() throws SQLException, JAXBException {
        try (Connection connection = getConnection(CONNECTION_URL)) {
            final DatabaseMetaData database = connection.getMetaData();
            try (ResultSet results
                    = database.getTables(null, null, null, null)) {
                final List<ResultSetMetaDataColumn> columns
                        = ResultSetMetaDataColumn.bind(results);
                for (final ResultSetMetaDataColumn column : columns) {
                    logger.debug("DatabaseMetaData.table: {}", column);
                    JaxbTest.marshalInstance(column);
                }
            }
        }
    }
}
