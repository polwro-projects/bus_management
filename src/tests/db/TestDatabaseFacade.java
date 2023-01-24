/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http:www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package tests.db;

import static org.junit.Assert.*;

import db.auth.AccountType;
import db.entities.*;
import db.strategies.*;
import fakes.db.FakeDatabaseFacade;
import fakes.db.auth.FakeAuthenticator;
import fakes.db.strategies.FakeManagerStrategy;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestDatabaseFacade {
	private FakeDatabaseFacade mFacade;

	@Before
	public void setUp() {
		mFacade = new FakeDatabaseFacade();
	}

	@Test(expected = RuntimeException.class)
	public void testGetLineDeath() {
		mFacade.getLine("");
	}

	@Test(expected = RuntimeException.class)
	public void testGetBusDeath() {
		mFacade.getBus(0);
	}

	@Test(expected = RuntimeException.class)
	public void testGetStationDeath() {
		mFacade.getStation("");
	}

	@Test
	public void testAddFailNoStrategy() {
		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.add(bus));
	}

	@Test
	public void testRemoveFailNoStrategy() {
		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.remove(bus));
	}

	@Test
	public void testUpdateFailNoStrategy() {
		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.update(bus));
	}

	@Test
	public void testUpdateStrategyManager() {
		mFacade.updateStrategy(AccountType.MANAGER);
		assertTrue(mFacade.getStrategy() instanceof ManagerStrategy);
	}

	@Test
	public void testUpdateStrategyUser() {
		mFacade.updateStrategy(AccountType.USER);
		assertTrue(mFacade.getStrategy() instanceof UserStrategy);
	}

	@Test(expected = RuntimeException.class)
	public void testUpdateStrategyFail() {
		mFacade.updateStrategy(AccountType.NONE);
	}

	@Test
	public void testOnAuthenticationStatusChangedManager() {
		FakeAuthenticator authenticator = new FakeAuthenticator(mFacade);
		authenticator.setAccountType(AccountType.MANAGER);

		mFacade.setAuthenticator(authenticator);
		mFacade.onAuthenticationStatusChanged();
		assertTrue(mFacade.getStrategy() instanceof ManagerStrategy);
	}

	@Test
	public void testOnAuthenticationStatusChangedUser() {
		FakeAuthenticator authenticator = new FakeAuthenticator(mFacade);
		authenticator.setAccountType(AccountType.USER);

		mFacade.setAuthenticator(authenticator);
		mFacade.onAuthenticationStatusChanged();
		assertTrue(mFacade.getStrategy() instanceof UserStrategy);
	}

	@Test(expected = RuntimeException.class)
	public void testOnAuthenticationStatusChangedFail() {
		FakeAuthenticator authenticator = new FakeAuthenticator(mFacade);

		mFacade.setAuthenticator(authenticator);
		authenticator.setAccountType(AccountType.NONE);
		mFacade.onAuthenticationStatusChanged();
	}

	@Test
	public void testGetAuthenticator() {
		FakeAuthenticator authenticator = new FakeAuthenticator(mFacade);

		mFacade.setAuthenticator(authenticator);
		assertEquals(mFacade.getAuthenticator(), authenticator);
	}

	@Test
	public void testGetStationsFail() {
		ArrayList<Station> stations = mFacade.getStations();
		assertTrue(stations.isEmpty());
	}

	@Test
	public void testGetLine() {
		Line line = new Line(0, "A");
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setEntity(line);
		mFacade.setStrategy(strategy);

		assertTrue(mFacade.getLine("A").equals(line));

		Line obtainedLine = mFacade.getLine("A");
		assertNotNull(obtainedLine);
		assertTrue(line.equals(obtainedLine));
	}

	@Test
	public void testGetBus() {
		Bus bus = new Bus(0, "A", "B");
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setEntity(bus);
		mFacade.setStrategy(strategy);

		Bus obtainedBus = mFacade.getBus(0);
		assertNotNull(obtainedBus);
		assertTrue(bus.equals(obtainedBus));
	}

	@Test
	public void testGetStation() {
		Station station = new Station(0, "A");
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setEntity(station);
		mFacade.setStrategy(strategy);

		Station obtainedStation = mFacade.getStation("A");
		assertNotNull(obtainedStation);
		assertTrue(station.equals(obtainedStation));
	}

	@Test
	public void testGetLineFail() {
		ManagerStrategy strategy = new ManagerStrategy(null);
		mFacade.setStrategy(strategy);

		assertNull(mFacade.getLine("A"));
	}

	@Test
	public void testGetBusFail() {
		ManagerStrategy strategy = new ManagerStrategy(null);
		mFacade.setStrategy(strategy);

		assertNull(mFacade.getBus(0));
	}

	@Test
	public void testGetStationFail() {
		ManagerStrategy strategy = new ManagerStrategy(null);
		mFacade.setStrategy(strategy);

		assertNull(mFacade.getStation("A"));
	}

	@Test
	public void testAdd() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(true);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertTrue(mFacade.add(bus));
	}

	@Test
	public void testRemoveTrue() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(true);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertTrue(mFacade.remove(bus));
	}

	@Test
	public void testUpdate() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(true);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertTrue(mFacade.update(bus));
	}

	@Test
	public void testAddFail() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(false);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.add(bus));
	}

	@Test
	public void testRemoveFail() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(false);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.remove(bus));
	}

	@Test
	public void testUpdateFail() {
		FakeManagerStrategy strategy = new FakeManagerStrategy();
		strategy.setOperationResult(false);
		mFacade.setStrategy(strategy);

		Bus bus = new Bus(0, "A", "B");
		assertFalse(mFacade.update(bus));
	}
}