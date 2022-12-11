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
<sling:adaptTo adaptable="${resource}" adaptTo="org.apache.sling.cms.PageManager" var="pageManager" />
<sling:adaptTo adaptable="${resource}" adaptTo="org.apache.sling.cms.ComponentPolicyManager" var="componentPolicyMgr" />
<c:set var="formConfig" value="${componentPolicyMgr.componentPolicy.componentConfigs['reference/components/forms/form'].valueMap}" />
<form class="${formConfig.formClass}" action="${pageManager.page.path}.allowpost.html/j_security_check" method="post" data-analytics-id="Login Form">  
    <c:if test="${not empty param.j_reason}">
        <div class="${formConfig.alertClass}">
            ${sling:encode(properties.errorMessage,'HTML')}
        </div>
    </c:if>
    <div class="${formConfig.fieldGroupClass}">
        <label for="j_username" class="label">${sling:encode(properties.usernameLabel,'HTML')} <span class="${formConfig.fieldRequiredClass}">*</span></label>
        <input type="text" class="${formConfig.fieldClass}" required="required" name="j_username" />
    </div>
    <div class="${formConfig.fieldGroupClass}">
        <label for="j_password" class="label">${sling:encode(properties.passwordLabel,'HTML')} <span class="${formConfig.fieldRequiredClass}">*</span></label>
        <input type="password" class="${formConfig.fieldClass}" required="required" name="j_password" />
    </div>
    <input type="hidden" name="resource" value="${sling:encode(properties.successPage,'HTML_ATTR')}.html" />
    <input type="hidden" name="j_validate" value="true" />
    <div class="${formConfig.fieldGroupClass}">
        <button class="${formConfig.submitClass}">${sling:encode(properties.submitLabel,'HTML')}</button>
    </div>
</form>