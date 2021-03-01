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
package com.exadel.aem.toolkit.api.annotations.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.exadel.aem.toolkit.api.annotations.meta.ResourceType;
import com.exadel.aem.toolkit.api.annotations.meta.ResourceTypes;
import com.exadel.aem.toolkit.api.annotations.widgets.attribute.Attribute;

/**
 * Used to define specific tab in multi-tab TouchUI dialog setup
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ResourceType(ResourceTypes.TABS)
@Deprecated
public @interface Tab {

    /**
     * Maps to the 'jcr:title' attribute of a {@code cq:dialog/content/items/tabs/items/<this_tab>} node
     * @return String value, required
     */
    String title();

    /**
     * When set to non-default, renders as set of specific attributes of a tab node
     * @see Attribute
     * @return {@code Attribute} value
     */
    Attribute attribute() default @Attribute;
}
