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

package com.exadel.aem.toolkit.test.mvc.component1.views;

import com.exadel.aem.toolkit.api.annotations.container.PlaceOn;
import com.exadel.aem.toolkit.api.annotations.container.Tab;
import com.exadel.aem.toolkit.api.annotations.main.DesignDialog;
import com.exadel.aem.toolkit.api.annotations.main.DialogLayout;
import com.exadel.aem.toolkit.api.annotations.widgets.Checkbox;
import com.exadel.aem.toolkit.api.annotations.widgets.DialogField;
import com.exadel.aem.toolkit.api.annotations.widgets.FieldSet;
import com.exadel.aem.toolkit.api.annotations.widgets.PathField;
import com.exadel.aem.toolkit.api.annotations.widgets.TextField;
import com.exadel.aem.toolkit.api.annotations.widgets.attribute.Attribute;
import com.exadel.aem.toolkit.api.annotations.widgets.attribute.Data;
import com.exadel.aem.toolkit.api.annotations.widgets.radio.RadioButton;
import com.exadel.aem.toolkit.api.annotations.widgets.radio.RadioGroup;
import com.exadel.aem.toolkit.api.annotations.widgets.select.Option;
import com.exadel.aem.toolkit.api.annotations.widgets.select.Select;
import com.exadel.aem.toolkit.core.util.TestConstants;

import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_1;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_2;
import static com.exadel.aem.toolkit.core.util.TestConstants.LABEL_TAB_3;

@DesignDialog(
    title = TestConstants.DEFAULT_COMPONENT_TITLE,
    layout = DialogLayout.TABS,
    tabs = {
        @Tab(title = LABEL_TAB_1),
        @Tab(title = LABEL_TAB_2),
        @Tab(title = LABEL_TAB_3)
    }
)
@SuppressWarnings("unused")
public class DesignDialogView implements Addition, Addition2 {

    @DialogField(
        label = "Field 1",
        description = "This is the first field.",
        wrapperClass = "my-class",
        renderHidden = true)
    @TextField
    @Attribute(
        id = "field1-id",
        className = "field1-attribute-class",
        data = {
            @Data(name = "field1-data1", value = "value-data1"),
            @Data(name = "field1-data2", value = "value-data2")
        })
    @PlaceOn(LABEL_TAB_1)
    String field1;

    @DialogField(label = "Field 2")
    @PathField(rootPath = "/content")
    @PlaceOn(LABEL_TAB_2)
    String field2;

    @DialogField(label = "Field 2.1", wrapperClass = "my-wrapper-class")
    @TextField
    @PlaceOn(LABEL_TAB_3)
    String field3;

    @DialogField(description = "This is the second second field")
    @Checkbox(text = "Checkbox 2")
    @PlaceOn(LABEL_TAB_1)
    String field4;

    @FieldSet(title = "Field set example")
    @PlaceOn(LABEL_TAB_2)
    FieldSetExample fieldSet;

    static class FieldSetExample {
        @DialogField
        @TextField
        String field6;

        @DialogField
        @TextField
        String field7;

        @DialogField
        @RadioGroup(buttons = {
            @RadioButton(text = "Button 1", value = "1"),
            @RadioButton(text = "Button 2", value = "2"),
            @RadioButton(text = "Button 3", value = "3")
        })
        String field8;
    }

    @DialogField(ranking = 200)
    @TextField
    @PlaceOn(LABEL_TAB_3)
    String getDropdown2() {
        return null;
    }

    @Override
    public String dropdown3() {
        return null;
    }

}

interface Addition {

    @DialogField(label = "Rating", ranking = 100)
    @Select(options = {
        @Option(text = "1 star", value = "1"),
        @Option(text = "2 star", value = "2"),
        @Option(text = "3 star", value = "3"),
        @Option(text = "4 star", value = "4"),
        @Option(text = "5 star", value = "5")
    }, emptyText = "Select rating")
    @PlaceOn(LABEL_TAB_3)
    default String getDropdown() {
        return null;
    }
}

interface Addition2 {

    @DialogField(ranking = 300)
    @TextField(value = "text")
    @PlaceOn(LABEL_TAB_3)
    String dropdown3();
}
