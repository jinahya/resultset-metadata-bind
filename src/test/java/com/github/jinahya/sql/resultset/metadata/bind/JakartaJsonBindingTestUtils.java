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
import jakarta.json.bind.JsonbConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

/**
 * Utilities for Jakarta JSON Binding tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JakartaJsonBindingTestUtils {

    /**
     * Writes the supplied value to the specified JSON file.
     *
     * @param value the value to write
     * @param path  the destination path
     * @throws IOException if the file cannot be written
     */
    static void write(final Object value, final Path path) throws IOException {
        requireNonNull(value, "value is null");
        requireNonNull(path, "path is null");
        try (var jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
             var writer = Files.newBufferedWriter(path)) {
            jsonb.toJson(value, writer);
        } catch (final IOException ioe) {
            throw ioe;
        } catch (final Exception e) {
            throw new IOException("failed to close JSON-B", e);
        }
    }

    /**
     * Reads a value of the specified type from the specified JSON file.
     *
     * @param path the source path
     * @param type the expected type
     * @param <T>  the value type
     * @return the value read from the file
     * @throws IOException if the file cannot be read
     */
    static <T> T read(final Path path, final Class<T> type) throws IOException {
        requireNonNull(path, "path is null");
        requireNonNull(type, "type is null");
        try (var jsonb = JsonbBuilder.create();
             var reader = Files.newBufferedReader(path)) {
            return jsonb.fromJson(reader, type);
        } catch (final IOException ioe) {
            throw ioe;
        } catch (final Exception e) {
            throw new IOException("failed to close JSON-B", e);
        }
    }

    JakartaJsonBindingTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
