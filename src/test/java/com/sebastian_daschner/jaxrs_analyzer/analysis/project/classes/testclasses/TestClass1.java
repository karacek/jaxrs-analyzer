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

package com.sebastian_daschner.jaxrs_analyzer.analysis.project.classes.testclasses;

import com.sebastian_daschner.jaxrs_analyzer.builder.ClassResultBuilder;
import com.sebastian_daschner.jaxrs_analyzer.builder.HttpResponseBuilder;
import com.sebastian_daschner.jaxrs_analyzer.builder.MethodResultBuilder;
import com.sebastian_daschner.jaxrs_analyzer.model.rest.HttpMethod;
import com.sebastian_daschner.jaxrs_analyzer.model.results.ClassResult;
import com.sebastian_daschner.jaxrs_analyzer.model.results.MethodResult;
import com.sebastian_daschner.jaxrs_analyzer.model.types.Types;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestClass1 {

    @GET
    public int method() {
        int status = 200;
        int anotherStatus = 100;
        status = anotherStatus = 300;
        return status;
    }

    public static ClassResult getResult() {
        final MethodResult methodResult = MethodResultBuilder.withResponses(HttpResponseBuilder.newBuilder().andEntityTypes(Types.PRIMITIVE_INT).build())
                .andMethod(HttpMethod.GET).build();
        return ClassResultBuilder.withResourcePath("test").andMethods(methodResult).build();
    }

}
