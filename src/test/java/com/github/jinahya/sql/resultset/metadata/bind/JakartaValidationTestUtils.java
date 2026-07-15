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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Utilities for Jakarta Validation tests.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JakartaValidationTestUtils {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(VALIDATOR_FACTORY::close));
    }

    /**
     * Validates the specified object.
     *
     * @param object the object to validate
     * @param <T>    the object type
     * @return the resulting constraint violations
     */
    static <T> Set<ConstraintViolation<T>> validate(final T object) {
        requireNonNull(object, "object is null");
        return VALIDATOR_FACTORY.getValidator().validate(object);
    }

    /**
     * Asserts that the specified object has no constraint violations.
     *
     * @param object the object to validate
     */
    static void requireValid(final Object object) {
        requireNonNull(object, "object is null");
        final var violations = validate(object);
        assertThat(violations)
                .as("violations of %s: %s", object, violations)
                .isEmpty();
    }

    private JakartaValidationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
