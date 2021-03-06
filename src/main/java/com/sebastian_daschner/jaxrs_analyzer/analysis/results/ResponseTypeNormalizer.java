/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.jaxrs_analyzer.analysis.results;

import com.sebastian_daschner.jaxrs_analyzer.model.types.Types;
import com.sebastian_daschner.jaxrs_analyzer.model.types.Type;

import javax.ws.rs.core.GenericEntity;

/**
 * Normalizes the request/response body Java types.
 *
 * @author Sebastian Daschner
 */
final class ResponseTypeNormalizer {

    private ResponseTypeNormalizer() {
        throw new UnsupportedOperationException();
    }

    /**
     * Normalizes all known nested types.
     *
     * @param type The type
     * @return The fully normalized type
     */
    static Type normalize(final Type type) {
        Type currentType = type;
        Type lastType;
        do {
            lastType = currentType;
            currentType = normalizeCollection(currentType);
            currentType = normalizeResponseWrapper(currentType);
        } while (!lastType.equals(currentType));

        return currentType;
    }

    /**
     * Normalizes the contained collection type.
     *
     * @param type The type
     * @return The normalized type
     */
    static Type normalizeCollection(final Type type) {
        if (type.isAssignableTo(Types.COLLECTION)) {
            if (!type.getTypeParameters().isEmpty()) {
                return type.getTypeParameters().get(0);
            }
            return Types.OBJECT;
        }
        return type;
    }

    /**
     * Normalizes the body type (e.g. removes nested {@link GenericEntity}s).
     *
     * @param type The type
     * @return The normalized type
     */
    static Type normalizeResponseWrapper(final Type type) {
        if (!type.getTypeParameters().isEmpty() && type.isAssignableTo(Types.GENERIC_ENTITY))
            return type.getTypeParameters().get(0);
        return type;
    }

}
