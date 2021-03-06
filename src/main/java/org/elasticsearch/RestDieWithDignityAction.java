/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestDieWithDignityAction extends BaseRestHandler {

    RestDieWithDignityAction() {
        super();
    }

    @Override
    public List<RestHandler.Route> routes() {
        RestHandler.Route route = new RestHandler.Route(RestRequest.Method.GET, "/_die_with_dignity");
        List<RestHandler.Route> routes = new ArrayList<RestHandler.Route>();
        routes.add(route);
        return routes;
    }

    @Override
    public String getName() {
        return "die_with_dignity_action";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        // This should throw an OOM which generates a heap dump
        long[] allocation = new long[Integer.MAX_VALUE];
        // If that doesn't work, manually throw an OOM. This won't generate a heap dump
        throw new OutOfMemoryError("die with dignity");
    }
}
