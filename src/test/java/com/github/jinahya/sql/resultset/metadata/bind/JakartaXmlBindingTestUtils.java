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

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

/**
 * Utilities for Jakarta XML Binding tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JakartaXmlBindingTestUtils {

    /**
     * Marshals the supplied value to the specified XML file.
     *
     * @param value the value to marshal
     * @param path  the destination path
     * @throws JAXBException if the value cannot be marshalled
     */
    static void marshal(final Object value, final Path path) throws JAXBException {
        requireNonNull(value, "value is null");
        requireNonNull(path, "path is null");
        final var marshaller = JAXBContext.newInstance(value.getClass()).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(value, path.toFile());
    }

    /**
     * Unmarshals a value of the specified type from the specified XML file.
     *
     * @param path the source path
     * @param type the expected type
     * @param <T>  the value type
     * @return the unmarshalled value
     * @throws JAXBException if the value cannot be unmarshalled
     */
    static <T> T unmarshal(final Path path, final Class<T> type) throws JAXBException {
        requireNonNull(path, "path is null");
        requireNonNull(type, "type is null");
        return JAXBContext.newInstance(type).createUnmarshaller()
                .unmarshal(new StreamSource(path.toFile()), type)
                .getValue();
    }

    JakartaXmlBindingTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
