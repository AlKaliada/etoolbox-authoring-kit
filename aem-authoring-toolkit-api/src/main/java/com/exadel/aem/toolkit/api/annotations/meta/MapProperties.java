/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exadel.aem.toolkit.api.annotations.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks that properties of a specific annotation are expected to be automatically mapped to attributes of a markup
 * entity. This setting extends to all the properties of annotation unless a narrower list is specified via {@link MapProperties#value()}
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapProperties {

    /**
     * When initialized to a non-blank value, allows setting name prefix for a current field or all the eligible fields
     * of current annotation
     * @return String value
     */
    String prefix() default "";

    /**
     * When initialized to a nonempty value, indicates that only properties with specified names will be automatically
     * mapped. Default or non-initialized value means that every property of an appropriate type (string, number, enum)
     * will be mapped
     * @return Array of strings, or an empty array
     */
    String[] value() default {};
}
