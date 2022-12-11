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
<datalist id="labelfield-${fn:replace(resource.name,':','-')}">
    <c:choose>
        <c:when test="${not empty properties.options}">
            <c:forEach var="option" items="${properties.options}">
                <c:set var="label" value="${fn:split(option,'=')[0]}" />
                <c:set var="value" value="${fn:split(option,'=')[1]}" />
                <c:choose>
                    <c:when test="${val eq value}">
                        <c:set var="selected" value="selected=\"selected\"" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="selected" value="" />
                    </c:otherwise>
                </c:choose>
                <fmt:message key="${label}" var="label" />
                <option ${selected} value="${sling:encode(value,'HTML_ATTR')}"><sling:encode value="${label}" mode="HTML" /></option>
            </c:forEach>
        </c:when>
        <c:when test="${sling:getRelativeResource(resource,'options') != null}">
            <c:forEach var="option" items="${sling:listChildren(sling:getRelativeResource(resource,'options'))}">
                <c:choose>
                    <c:when test="${option.valueMap.value eq editProperties[properties.name]}">
                        <c:set var="selected" value="selected=\"selected\"" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="selected" value="" />
                    </c:otherwise>
                </c:choose>
                <fmt:message key="${option.valueMap.label}" var="label" />
                <option ${selected} value="${sling:encode(option.valueMap.value,'HTML_ATTR')}"><sling:encode value="${label}" mode="HTML" /></option>
            </c:forEach>
        </c:when>
        <c:when test="${not empty properties.optionsScript}">
            <sling:call script="${properties.optionsScript}" />
        </c:when>
    </c:choose>
</datalist>