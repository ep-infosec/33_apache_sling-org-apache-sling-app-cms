<%-- /*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */ --%>
 <%@include file="/libs/sling-cms/global.jsp"%>
 <div class="labelfield">
     <fieldset disabled="disabled" class="labelfield__template is-hidden">
         <a class="tag labelfield__item" title="">
            <input type="hidden" name="${sling:encode(properties.name,'HTML_ATTR')}" value="" />
            <span class="labelfield__title">
            </span>
            <button class="delete is-small">
                <span class="is-sr-only">
                    <fmt:message key="Delete" />
                </span>
            </button>
        </a>
    </fieldset>
    <div class="labelfield__field field has-addons">
        <div class="control is-expanded">
             <input class="input" type="text" ${required} ${disabled} id="${sling:encode(properties.name,'HTML_ATTR')}" list="labelfield-${fn:replace(resource.name,':','-')}" autocomplete="off" />
         </div>
         <div class="control">
             <button class="labelfield__add button">
                 <span class="jam jam-plus">
                    <span class="is-sr-only">
                        <fmt:message key="Add" />
                    </span>
                 </span>
             </button>
         </div>
     </div>
     <div class="labelfield__container tags are-large">
         <sling:call script="values.jsp" />
     </div>
    <sling:call script="options" />
    <input type="hidden" name="${sling:encode(properties.name,'HTML_ATTR')}@TypeHint" value="String[]"/>
    <input type="hidden" name="${sling:encode(properties.name,'HTML_ATTR')}@Delete" value="delete" />
</div>
