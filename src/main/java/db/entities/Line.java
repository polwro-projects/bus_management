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

package db.entities;

import java.util.ArrayList;

public class Line extends Entity {
	String mName;
	ArrayList<Bus> mBuses = new ArrayList<Bus>();

	public Line(int id, String name) {
		super(id);

		mName = name;
	}

	public Line(Line another) {
		super(another);

		this.mName = another.mName;
		this.mBuses = new ArrayList<Bus>(another.mBuses);
	}

	public String getName() {
		return mName;
	}

	public ArrayList<Bus> getBuses() {
		return mBuses;
	}

	public boolean removeBus(Bus value) {
		// Try to find the bus
		for (Bus temporaryBus : mBuses) {
			// Return false, if the bus with such ID is present
			if (temporaryBus.getId() == value.getId()) {
				mBuses.remove(temporaryBus);
				return true;
			}
		}

		return false;
	}
	public boolean addBus(Bus value) {
		// Don't do anything, if the bus is null
		if (value == null) {
			return false;
		}

		// Try to find the bus
		for (Bus temporaryBus : mBuses) {
			// Return false, if the bus with such ID is present
			if (temporaryBus.getId() == value.getId()) {
				return false;
			}
		}
		mBuses.add(value);

		return true;
	}

	@Override
	public boolean equals(Object another) {
		// If the object is compared with itself then return true
		if (another == this) {
			return true;
		}

		// Check if thy objects are of the same type
		if (!(another instanceof Line)) {
			return false;
		}

		// Compare the data members
		Line line = (Line) another;
		return super.equals(another) && mName == line.mName && mBuses == line.mBuses;
	}
}