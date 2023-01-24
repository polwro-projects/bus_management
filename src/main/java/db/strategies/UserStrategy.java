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

package db.strategies;

import db.entities.*;
import java.sql.*;
import java.util.ArrayList;

public class UserStrategy extends AbstractDatabaseStrategy {
	// FIXME : dummy implementation, remove
	protected ArrayList<Entity> mEntities = new ArrayList<Entity>();

	public UserStrategy(Connection connection) {
		super(connection);
	}

	@Override
	public boolean add(Entity value) {
		// TODO
		return false;
	}

	@Override
	public boolean remove(Entity value) {
		// TODO
		return false;
	}

	@Override
	public boolean update(Entity value) {
		// TODO

		return true;
	}

	@Override
	public Line getLine(String name) {
		// TODO

		// FIXME : dummy implementation, remove
		for (Entity entity : mEntities) {
			if (!(entity instanceof Line)) {
				continue;
			}

			Line line = (Line) entity;
			if (line.getName() == name) {
				return new Line(line);
			}
		}
		return null;
	}

	@Override
	public Bus getBus(int id) {
		// TODO

		// FIXME : dummy implementation, remove
		for (Entity entity : mEntities) {
			if (!(entity instanceof Bus)) {
				continue;
			}

			Bus bus = (Bus) entity;
			if (bus.getId() == id) {
				return new Bus(bus);
			}
		}

		return null;
	}

	@Override
	public Station getStation(String name) {
		// TODO

		// FIXME : dummy implementation, remove
		for (Entity entity : mEntities) {
			if (!(entity instanceof Station)) {
				continue;
			}

			Station station = (Station) entity;
			if (station.getName() == name) {
				return new Station(station);
			}
		}

		return null;
	}

	@Override
	public ArrayList<Station> getStations() {
		// TODO

		ArrayList<Station> stations = new ArrayList<Station>();
		return stations;
	}
}