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
<div class="modal-background"></div>
<div class="modal-content is-draggable">
    <div class="box">
        <fmt:message key="${properties['jcr:title']}" var="title" />
        <h3 class="modal-title"><sling:encode value="${title}" mode="HTML" /></h3>
        <sling:include path="container" resourceType="sling-cms/components/general/container" />
    </div>
</div>
<button class="modal-close is-large" aria-label="close"></button>