/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.cms.core.internal;

import java.util.Optional;

import javax.script.Bindings;
import javax.servlet.ServletRequest;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.cms.CMSConstants;
import org.apache.sling.cms.CMSUtils;
import org.apache.sling.cms.ComponentConfiguration;
import org.apache.sling.cms.Page;
import org.apache.sling.scripting.api.BindingsValuesProvider;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = BindingsValuesProvider.class, property = { Constants.SERVICE_RANKING + "=100" })
public class DefaultScriptBindingsValueProvider implements BindingsValuesProvider {

    public static final String PN_CURRENT_PAGE = "currentPage";

    @Override
    public void addBindings(Bindings bindings) {
        Resource resource = (Resource) bindings.get("resource");

        bindings.put("properties", resource.getValueMap());

        Optional.ofNullable(resource.adaptTo(ComponentConfiguration.class)).map(ComponentConfiguration::getProperties)
                .ifPresent(p -> bindings.put("componentConfiguration", p));

        Resource publishableParent = CMSUtils.findPublishableParent(resource);
        if (publishableParent != null && CMSConstants.NT_PAGE.equals(publishableParent.getResourceType())) {
            Optional.of(publishableParent.adaptTo(Page.class)).ifPresent(p -> {
                bindings.put("page", p);
                ServletRequest request = (ServletRequest) bindings.get("request");
                if (request.getAttribute(PN_CURRENT_PAGE) == null) {
                    request.setAttribute(PN_CURRENT_PAGE, p);
                    bindings.put(PN_CURRENT_PAGE, p);
                } else {
                    bindings.put(PN_CURRENT_PAGE, request.getAttribute(PN_CURRENT_PAGE));
                }
            });
        }

    }
}