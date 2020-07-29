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

package com.exadel.aem.toolkit.test.dependson;

import com.exadel.aem.toolkit.api.annotations.assets.dependson.DependsOnRef;
import com.exadel.aem.toolkit.api.annotations.assets.dependson.DependsOnRefTypes;
import com.exadel.aem.toolkit.api.annotations.main.Dialog;
import com.exadel.aem.toolkit.api.annotations.main.DialogLayout;
import com.exadel.aem.toolkit.api.annotations.widgets.DialogField;
import com.exadel.aem.toolkit.api.annotations.widgets.PathField;
import com.exadel.aem.toolkit.api.annotations.widgets.TextField;
import com.exadel.aem.toolkit.api.annotations.widgets.imageupload.ImageUpload;
import com.exadel.aem.toolkit.api.annotations.widgets.rte.RichTextEditor;

@Dialog(
        name = "test-component",
        title = "test-component-dialog",
        layout = DialogLayout.FIXED_COLUMNS
)
@SuppressWarnings("unused")
public class DependsOnRefAnnotation {
    @DialogField
    @ImageUpload
    @DependsOnRef
    private String defaultReferenceName;

    @DialogField
    @TextField
    @DependsOnRef(name = "nameOnly")
    private String referenceName;

    @DialogField
    @PathField
    @DependsOnRef(name = "nameAndType", type = DependsOnRefTypes.BOOLSTRING)
    private String referenceNameAndType;

    @DialogField
    @TextField
    @DependsOnRef(name = "lazyReference", lazy = true)
    private String lazyReference;

    @DialogField
    @RichTextEditor
    @DependsOnRef(type = DependsOnRefTypes.AUTO)
    private String typeAuto;
}
