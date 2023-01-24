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

package db;

import db.auth.*;
import db.entities.*;
import db.strategies.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseFacade {
	protected Connection mConnection;
	protected IDatabaseStrategy mDatabaseStrategy;
	protected Authenticator mAuthenticator = new Authenticator(this, mConnection);

	public Line getLine(String name) {
		if (mDatabaseStrategy == null) {
			throw new RuntimeException("Database strategy is not defined. Abort.");
		}
		return mDatabaseStrategy.getLine(name);
	}

	public Bus getBus(int id) {
		if (mDatabaseStrategy == null) {
			throw new RuntimeException("Database strategy is not defined. Abort.");
		}
		return mDatabaseStrategy.getBus(id);
	}

	public Station getStation(String name) {
		if (mDatabaseStrategy == null) {
			throw new RuntimeException("Database strategy is not defined. Abort.");
		}
		return mDatabaseStrategy.getStation(name);
	}

	public boolean add(Entity value) {
		if (mDatabaseStrategy == null) {
			return false;
		}
		return mDatabaseStrategy.add(value);
	}

	public boolean remove(Entity value) {
		if (mDatabaseStrategy == null) {
			return false;
		}
		return mDatabaseStrategy.remove(value);
	}

	public boolean update(Entity value) {
		if (mDatabaseStrategy == null) {
			return false;
		}
		return mDatabaseStrategy.update(value);
	}

	public void onAuthenticationStatusChanged() {
		AccountType type = mAuthenticator.getAccountType();
		updateStrategy(type);
	}

	public ArrayList<Station> getStations() {
		if (mDatabaseStrategy == null) {
			return new ArrayList<Station>();
		}

		return mDatabaseStrategy.getStations();
	}

	public Authenticator getAuthenticator() {
		return mAuthenticator;
	}

	protected void updateStrategy(AccountType type) {
		switch (type) {
			case USER:
				mDatabaseStrategy = new UserStrategy(mConnection);
				break;
			case MANAGER:
				mDatabaseStrategy = new ManagerStrategy(mConnection);
				break;
			default:
				throw new RuntimeException("Strategy is not expected. Abort.");
		}
	}
}