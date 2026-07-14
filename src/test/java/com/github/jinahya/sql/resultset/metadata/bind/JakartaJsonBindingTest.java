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

import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests Jakarta JSON Binding support for the package's binding types.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class JakartaJsonBindingTest {

    /**
     * Verifies that a default JSON-B instance serializes fields directly.
     */
    @Test
    public void toJsonSerializesFields() throws Exception {
        final var value = new ResultSetMetaDataColumn();
        value.setColumnName("COLUMN_NAME");
        value.setColumnType(12);
        try (var jsonb = JsonbBuilder.create()) {
            final var json = jsonb.toJson(value);
            assertTrue(json.contains("\"columnName\":\"COLUMN_NAME\""), json);
            assertTrue(json.contains("\"columnType\":12"), json);
        }
    }

    /**
     * Verifies that JSON-B serializes a list directly as a JSON array.
     */
    @Test
    public void toJsonSerializesListDirectly() throws Exception {
        final var first = new ResultSetMetaDataColumn();
        first.setColumnName("FIRST");
        final var second = new ResultSetMetaDataColumn();
        second.setColumnName("SECOND");
        try (var jsonb = JsonbBuilder.create()) {
            final var json = jsonb.toJson(List.of(first, second));
            assertTrue(json.startsWith("["), json);
            assertTrue(json.endsWith("]"), json);
            assertTrue(json.contains("\"columnName\":\"FIRST\""), json);
            assertTrue(json.contains("\"columnName\":\"SECOND\""), json);
        }
    }

    /**
     * Verifies that the package-level visibility strategy supports deserialization through field access.
     */
    @Test
    public void fromJsonPopulatesFields() throws Exception {
        final var json = "{\"columnName\":\"COLUMN_NAME\",\"columnType\":12,\"readOnly\":true}";
        try (var jsonb = JsonbBuilder.create()) {
            final var actual = jsonb.fromJson(json, ResultSetMetaDataColumn.class);
            assertNotNull(actual);
            assertEquals("COLUMN_NAME", actual.getColumnName());
            assertEquals(12, actual.getColumnType());
            assertTrue(actual.isReadOnly());
            assertFalse(actual.isWritable());
        }
    }
}
