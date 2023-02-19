/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package unittests.db;

import static org.junit.Assert.*;

import fakes.db.entities.FakeEntity;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@Category(unittests.TestDBSuite.class)
public class TestEntity {
	private final FakeEntity mEntity;
	private final int mId;

	@Rule public Timeout globalTimeout = new Timeout(5);

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {{5}});
	}

	public TestEntity(int id) {
		this.mEntity = new FakeEntity(5);
		this.mId = id;
	}

	@Test
	public void copyConstructor() {
		FakeEntity anotherEntity = new FakeEntity(mId);

		assertTrue(mEntity.equals(anotherEntity));
	}

	@Test
	public void getId() {
		assertEquals(mEntity.getId(), mId);
	}
}