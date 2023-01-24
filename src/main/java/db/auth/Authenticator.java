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

package db.auth;

import db.DatabaseFacade;
import java.sql.*;

public class Authenticator {
	protected Connection mConnection;
	protected DatabaseFacade mDatabaseFacade;
	protected AccountType mAccountType = AccountType.NONE;

	public Authenticator(DatabaseFacade facade, Connection connection) {
		mDatabaseFacade = facade;
		mConnection = connection;
	}

	public boolean login(String email, String passwordHash) {
		// TODO

		// FIXME : dummy implementation, remove
		mAccountType = AccountType.MANAGER;
		notifyAccountTypeChanged();

		return true;
	}

	public boolean logout() {
		// TODO

		return true;
	}

	public AccountType getAccountType() {
		return mAccountType;
	}

	protected void notifyAccountTypeChanged() {
		if (mDatabaseFacade == null) {
			// TODO : throw an exception
			return;
		}

		mDatabaseFacade.onAuthenticationStatusChanged();
	}
}