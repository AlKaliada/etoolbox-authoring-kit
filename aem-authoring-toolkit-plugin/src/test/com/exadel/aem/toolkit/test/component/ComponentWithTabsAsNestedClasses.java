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

package com.exadel.aem.toolkit.test.component;

import com.exadel.aem.toolkit.api.annotations.container.IgnoreTabs;
import com.exadel.aem.toolkit.api.annotations.container.PlaceOnTab;
import com.exadel.aem.toolkit.api.annotations.container.Tab;
import com.exadel.aem.toolkit.api.annotations.main.Dialog;
import com.exadel.aem.toolkit.api.annotations.main.DialogLayout;
import com.exadel.aem.toolkit.api.annotations.widgets.DialogField;
import com.exadel.aem.toolkit.api.annotations.widgets.PathField;
import com.exadel.aem.toolkit.api.annotations.widgets.TextField;
import com.exadel.aem.toolkit.core.util.TestConstants;

import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_0;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_1;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_2;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_3;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_4;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_5;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_6;

@Dialog(name = TestConstants.DEFAULT_COMPONENT_NAME,
        title = TestConstants.DEFAULT_COMPONENT_TITLE,
        layout = DialogLayout.TABS,
        tabs = {
            @Tab(title = LABEL_TAB_5),
            @Tab(title = LABEL_TAB_6),
        }
)
@IgnoreTabs({LABEL_TAB_4, LABEL_TAB_5})
@SuppressWarnings("unused")
public class ComponentWithTabsAsNestedClasses extends ComponentWithTabsAsNestedClassesAncestor {

    @Tab(title = LABEL_TAB_1)
    private static class Tab1 {
        @DialogField(
                label = "Field 1",
                description = "This is the first field."
        )
        @TextField
        String field1;
    }

    @Tab(title = LABEL_TAB_2)
    private static class Tab2 {
        @DialogField(label="Field 2")
        @PathField(rootPath = "/content")
        @PlaceOnTab("Second tab")
        String field2;
    }

    @Tab(title = LABEL_TAB_3)
    private static class Tab3 extends Tab1 {
        @DialogField(
                label="Field 3",
                wrapperClass = "my-wrapper-class"
        )
        @TextField
        String field3;
    }

    @DialogField(ranking = -1)
    @TextField
    @PlaceOnTab(LABEL_TAB_0)
    String field5;
}

class ComponentWithTabsAsNestedClassesBase {
    @Tab(title = LABEL_TAB_0)
    private static class Tab0 {
        @DialogField
        @TextField
        String field0;
    }
}

@Dialog(
        name = TestConstants.DEFAULT_COMPONENT_NAME,
        title = TestConstants.DEFAULT_COMPONENT_TITLE,
        tabs = @Tab(title = LABEL_TAB_4)
)
class ComponentWithTabsAsNestedClassesAncestor extends ComponentWithTabsAsNestedClassesBase {}