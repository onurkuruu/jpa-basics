package com.oonurkuru.jpa.utils;

import com.oonurkuru.jpa.annotations.ExampleClassCode;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Onur Kuru on 16.7.2017.
 */
public abstract class AnnotationHandler {

    public static Map<String, Class> createClassMap() throws UnsupportedEncodingException {

        List<Class> classList = ClassFinder.findClassesByAnnotation(ExampleClassCode.class);
        Map<String, Class> mappedClassList = new HashMap<>();

        for (Class clazz : classList) {
            ExampleClassCode classCode = (ExampleClassCode) clazz.getAnnotation(ExampleClassCode.class);
            mappedClassList.put(classCode.classCode(), clazz);
        }

        return mappedClassList;
    }
}
