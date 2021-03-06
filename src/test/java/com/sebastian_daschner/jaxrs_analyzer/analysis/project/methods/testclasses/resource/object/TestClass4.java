package com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods.testclasses.resource.object;

import com.sebastian_daschner.jaxrs_analyzer.model.elements.HttpResponse;
import com.sebastian_daschner.jaxrs_analyzer.model.types.Type;

import java.util.Collections;
import java.util.Set;

public class TestClass4 {

    public Model method() {
        if ("".equals(""))
            return new Model("hi");
        return new Model("Hello World!");
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();
        result.getEntityTypes().add(new Type("com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods.testclasses.resource.object.TestClass4$Model"));

        return Collections.singleton(result);
    }

    private class Model {
        public Model(final String string) {
        }
    }

}
