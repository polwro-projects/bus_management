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

public class Station extends Entity {
	String mName;
	ArrayList<Line> mLines;

	public Station(int id, String name) {
		super(id);

		mName = name;
	}

	public Station(Station another) {
		super(another);

		another.mName = another.mName;
	}

	public String getName() {
		return mName;
	}

	public ArrayList<Line> getLines() {
		return mLines;
	}

	public boolean removeLine(Line value) {
		// TODO

		return true;
	}
	public boolean addBus(Line value) {
		// TODO

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
		Station station = (Station) another;
		return super.equals(another) && mName == station.mName && mLines == station.mLines;
	}
}