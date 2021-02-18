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

/**
 * @author Yana Bernatskaya (YanaBr)
 *
 * Coral 3 NumberInput accessor
 * */
(function ($, ns) {
    const NUMBERINPUT_SELECTOR = '.coral3-NumberInput';
    const NUMBERINPUT_INPUT_SELECTOR = '.coral3-NumberInput-input';

    ns.ElementAccessors.registerAccessor({
        selector: `${NUMBERINPUT_SELECTOR}`,
        visibility: function ($el, state) {
            $el.find(NUMBERINPUT_INPUT_SELECTOR).attr('type', state ? 'number' : 'hidden');
            ns.ElementAccessors.DEFAULT_ACCESSOR.visibility($el, state);
        }
    });
})(Granite.$, Granite.DependsOnPlugin = (Granite.DependsOnPlugin || {}));
