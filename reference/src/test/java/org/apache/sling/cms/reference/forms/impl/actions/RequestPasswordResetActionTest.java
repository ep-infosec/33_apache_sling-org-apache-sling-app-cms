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
package org.apache.sling.cms.reference.forms.impl.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import javax.jcr.AccessDeniedException;
import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFactory;
import javax.jcr.ValueFormatException;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.value.AbstractValueFactory;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.cms.reference.forms.FormActionResult;
import org.apache.sling.cms.reference.forms.FormException;
import org.apache.sling.cms.reference.forms.FormRequest;
import org.apache.sling.cms.reference.forms.impl.FormRequestImpl;
import org.apache.sling.servlethelpers.MockSlingHttpServletRequest;
import org.apache.sling.testing.resourceresolver.MockResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RequestPasswordResetActionTest {

    private ResourceResolverFactory factory;
    private ResourceResolver resolver;

    @Before
    public void init() throws FormException, LoginException, AccessDeniedException,
            UnsupportedRepositoryOperationException, RepositoryException {

        factory = Mockito.mock(ResourceResolverFactory.class);

        resolver = Mockito.mock(ResourceResolver.class);
        Mockito.when(factory.getServiceResourceResolver(Mockito.anyMap())).thenReturn(resolver);

        JackrabbitSession session = Mockito.mock(JackrabbitSession.class);
        Mockito.when(resolver.adaptTo(Mockito.any())).thenReturn(session);

        ValueFactory vf = new AbstractValueFactory() {

            @Override
            protected void checkPathFormat(String pathValue) throws ValueFormatException {
                // TODO Auto-generated method stub

            }

            @Override
            protected void checkNameFormat(String nameValue) throws ValueFormatException {
                // TODO Auto-generated method stub

            }

        };
        Mockito.when(session.getValueFactory()).thenReturn(vf);

        UserManager userManager = Mockito.mock(UserManager.class);
        Mockito.when(session.getUserManager()).thenReturn(userManager);

        Mockito.when(userManager.getAuthorizable("test@email.com")).thenReturn(Mockito.mock(User.class));

    }

    @Test
    public void testHandleForm() throws FormException {

        RequestPasswordResetAction action = new RequestPasswordResetAction(factory);

        FormRequest request = new FormRequestImpl(new MockSlingHttpServletRequest(resolver), null, null);
        request.getFormData().put("email", "test@email.com");

        Resource actionResource = new MockResource("/content",
                Collections.singletonMap(RequestPasswordResetAction.PN_RESETTIMEOUT, 2), null);

        FormActionResult result = action.handleForm(actionResource, request);
        assertTrue(result.isSucceeded());

    }

    @Test
    public void testNoUser() throws FormException {

        RequestPasswordResetAction action = new RequestPasswordResetAction(factory);

        FormRequest request = new FormRequestImpl(new MockSlingHttpServletRequest(resolver), null, null);
        request.getFormData().put("email", "test1@email.com");

        Resource actionResource = new MockResource("/content",
                Collections.singletonMap(RequestPasswordResetAction.PN_RESETTIMEOUT, 2), null);

        FormActionResult result = action.handleForm(actionResource, request);
        assertFalse(result.isSucceeded());

    }

    @Test
    public void testHandles() throws FormException {

        RequestPasswordResetAction action = new RequestPasswordResetAction(null);
        Resource validResource = Mockito.mock(Resource.class);
        Mockito.when(validResource.getResourceType()).thenReturn(RequestPasswordResetAction.RESOURCE_TYPE);
        assertTrue(action.handles(validResource));

        Resource inValidResource = Mockito.mock(Resource.class);
        Mockito.when(inValidResource.getResourceType()).thenReturn("something/else");
        assertFalse(action.handles(inValidResource));
    }

}
