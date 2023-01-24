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

import db.DatabaseFacade;
import db.auth.Authenticator;
import db.entities.*;
import java.util.Objects;

public class Application {
	static private DatabaseFacade mDatabaseFacade = new DatabaseFacade();
	static private Authenticator mAuthenticator = mDatabaseFacade.getAuthenticator();

	public static void main(String[] args) {
		// Login into the system
		if (!mAuthenticator.login("bherrington", "12345678")) {
			System.out.println("Login was not successful. Please, try again.");
		}
		System.out.println("Login finished successfully");

		// Create a line with buses
		Line line = new Line(0, "Dworec Autobusowy - pl.Grunwaldzki");
		line.addBus(new Bus(0, "A", "B"));
		line.addBus(new Bus(1, "C", "D"));

		// Add the line to the system
		if (!mDatabaseFacade.add(line)) {
			System.out.println("There was an error while trying to add line \"" + line.getName() + "\". Abort.");
			return;
		}
		System.out.println("Line \"" + line.getName() + "\" was added.");

		// Print the data from the database
		line = mDatabaseFacade.getLine("Dworec Autobusowy - pl.Grunwaldzki");
		if (Objects.isNull(line)) {
			System.out.println("Line was not added previously. Abort.");
			return;
		}
		System.out.println("Line \"" + line.getName() + "\" was found in database.");

		System.out.println("Line \"" + line.getName() + "\" has buses.");
		for (Bus bus : line.getBuses()) {
			System.out.println("   Bus with ID : " + bus.getId() + " has following parameters : " + bus.getBrand() + " "
				+ bus.getModel());
		}

		// Remove one of the buses and update the line in the database
		line.removeBus(new Bus(0, "A", "B"));
		if (!mDatabaseFacade.update(line)) {
			System.out.println(
				"There was an error while trying to update line \"" + line.getName() + "\". Nothing changed.");
			return;
		}
		System.out.println("Line \"" + line.getName() + "\" was updated. Bus with the index 0 was removed.");

		// Print the data from the database to check if it was updated
		line = mDatabaseFacade.getLine("Dworec Autobusowy - pl.Grunwaldzki");
		if (Objects.isNull(line)) {
			System.out.println("Line was not added previously. Abort.");
			return;
		}
		System.out.println("Line \"" + line.getName() + "\" was found in database.");

		System.out.println("Line \"" + line.getName() + "\" has buses.");
		for (Bus bus : line.getBuses()) {
			System.out.println("   Bus with ID : " + bus.getId() + " has following parameters : " + bus.getBrand() + " "
				+ bus.getModel());
		}
	}
}