package com.zuoxiaolong.niubi.job.core.helper;

/*
 * Copyright 2002-2015 the original author or authors.
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

/**
 * @author 左潇龙
 * @since 1/11/2016 20:17
 */
public abstract class ClassHelper {

    public static String getPackageName(String jarEntryName) {
        if (jarEntryName.endsWith(".class")) {
            jarEntryName = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
            int index = jarEntryName.lastIndexOf(".");
            if (index < 0 ) {
                return null;
            } else {
                return jarEntryName.substring(0, index);
            }
        }
        return null;
    }

    public static String getClassName(String jarEntryName) {
        if (jarEntryName.endsWith(".class")) {
            return jarEntryName.replace("/", ".").substring(0, jarEntryName.lastIndexOf("."));
        }
        return null;
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (classLoader == null) {
            // No thread context class loader -> use class loader of this class.
            classLoader = ClassHelper.class.getClassLoader();
            if (classLoader == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return classLoader;
    }

}
