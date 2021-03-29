/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
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
package com.exadel.aem.toolkit.plugin.handlers.widgets.common;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;

import com.exadel.aem.toolkit.api.annotations.meta.ResourceType;
import com.exadel.aem.toolkit.api.handlers.Source;
import com.exadel.aem.toolkit.api.handlers.Target;
import com.exadel.aem.toolkit.plugin.utils.DialogConstants;

/**
 * Handler for storing {@link ResourceType} and like properties to a Granite UI widget node
 */
public class ResourceTypeHandler implements BiConsumer<Source, Target> {

    /**
     * Processes the user-defined data and writes it to XML entity
     * @param source Current {@link Source} instance
     * @param target Current {@link Target} instance
     */
    @Override
    public void accept(Source source, Target target) {
        String resourceType = Arrays.stream(source.adaptTo(Annotation[].class))
            .map(annotation -> annotation.annotationType().getDeclaredAnnotation(ResourceType.class))
            .filter(Objects::nonNull)
            .map(ResourceType::value)
            .findFirst()
            .orElse(StringUtils.EMPTY);
        if (StringUtils.isBlank(resourceType)) {
            return;
        }
        target.attribute(DialogConstants.PN_SLING_RESOURCE_TYPE, resourceType);
    }
}
