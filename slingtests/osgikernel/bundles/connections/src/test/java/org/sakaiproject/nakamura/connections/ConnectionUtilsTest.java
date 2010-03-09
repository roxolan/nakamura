/*
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.sakaiproject.nakamura.connections;

import static org.easymock.EasyMock.replay;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */
public class ConnectionUtilsTest {

  private Authorizable user1;
  private Authorizable user2;
  
  @Before
  public void setUp() throws Exception {
    user1 = createMock(Authorizable.class);
    expect(user1.getID()).andReturn("user1").anyTimes();
    expect(user1.isGroup()).andReturn(false).anyTimes();

    user2 = createMock(Authorizable.class);
    expect(user2.getID()).andReturn("user2").anyTimes();
    expect(user2.isGroup()).andReturn(false).anyTimes();
    
    replay(user1, user2);
  }
  
  /**
   *
   */
  private static final String BASE = "/_user/b3/da/a7/7b/user1/contacts/a1/88/1c/06/user2";

  @Test
  public void testBasePath() {
    assertEquals(BASE, ConnectionUtils.getConnectionPath(user1, user2, null));
    assertEquals(BASE, ConnectionUtils.getConnectionPath(user1, user2));
  }

  @Test
  public void testPathEmpty() {
    assertEquals(BASE, ConnectionUtils.getConnectionPath(user1, user2, ""));
  }

  @Test
  public void testPathSelector() {
    assertEquals(BASE+".dsfljksfkjs.sdfsdf", ConnectionUtils.getConnectionPath(user1, user2, ".dsfljksfkjs.sdfsdf"));
  }

  @Test
  public void testPathStub() {
    assertEquals(BASE+"/sdfsd/sdfsdf", ConnectionUtils.getConnectionPath(user1, user2, "/sdfsd/sdfsdf"));
  }

  @Test
  public void testPathUserSelector() {
    assertEquals(BASE+".dfsdfds.sdfsdf", ConnectionUtils.getConnectionPath(user1, user2, ".dfsdfds.sdfsdf"));
  }
}
