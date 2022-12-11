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
package org.apache.sling.cms.core.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.jcr.query.Query;

import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Rule;
import org.junit.Test;

public class QueryDebuggerTest {

    @Rule
    public SlingContext context = new SlingContext(ResourceResolverType.JCR_OAK);

    @Test
    public void testNoParams() {
        QueryDebugger debugger = new QueryDebugger(context.request());
        assertNull(debugger.getException());
        assertNull(debugger.getPlan());
        assertNull(debugger.getStatement());

        assertNotNull(debugger.getPopularQueries());
        assertNotNull(debugger.getSlowQueries());
    }

    @Test
    public void testExplain() {
        context.request().addRequestParameter("statement", "SELECT * FROM [nt:base]");
        context.request().addRequestParameter("language", Query.JCR_SQL2);

        QueryDebugger debugger = new QueryDebugger(context.request());
        assertNotNull(debugger.getPlan());
        assertNull(debugger.getException());
        assertNotNull(debugger.getStatement());
    }

    @Test
    public void testFailure() {
        context.request().addRequestParameter("statement", "SELECT * FROM [nt:base]");
        context.request().addRequestParameter("language", Query.XPATH);

        QueryDebugger debugger = new QueryDebugger(context.request());
        assertNull(debugger.getPlan());
        assertNotNull(debugger.getException());
        assertNull(debugger.getStatement());
    }

}
