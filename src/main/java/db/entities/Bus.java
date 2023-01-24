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

public class Bus extends Entity {
	String mBrand;
	String mModel;

	public Bus(int id, String brand, String model) {
		super(id);

		mBrand = brand;
		mModel = model;
	}

	public Bus(Bus another) {
		super(another);

		this.mBrand = another.mBrand;
		this.mModel = another.mModel;
	}

	public String getBrand() {
		return mBrand;
	}

	public String getModel() {
		return mModel;
	}

	@Override
	public boolean equals(Object another) {
		// If the object is compared with itself then return true
		if (another == this) {
			return true;
		}

		// Check if thy objects are of the same type
		if (!(another instanceof Bus)) {
			return false;
		}

		// Compare the data members
		Bus bus = (Bus) another;
		return super.equals(another) && mBrand == bus.mBrand && mModel == bus.mModel;
	}
}