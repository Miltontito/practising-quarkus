/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.milton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.milton.Endpoint.endpoint;

public class ScenarioNumber extends ScenarioInvoker {

  // tag::adocSnippet[]
  private static final String targetUrl = "http://localhost:8081";

  private static final String contextRoot = "/api/numbers/book";

  @Override
  protected String getTargetUrl() {
    return targetUrl;
  }

  @Override
  protected List<Endpoint> getEndpoints() {
    return Stream.of(
      endpoint(contextRoot, "GET"),
      endpoint(contextRoot + "/ping", "GET")
    )
      .collect(collectingAndThen(toList(), Collections::unmodifiableList));
  }
  // end::adocSnippet[]
}
