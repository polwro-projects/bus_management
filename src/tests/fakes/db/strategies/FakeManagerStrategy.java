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

package fakes.db.strategies;

import db.entities.*;
import db.strategies.ManagerStrategy;

public class FakeManagerStrategy extends ManagerStrategy {
	protected boolean mOperationResult;
	protected Entity mEntity;

	public FakeManagerStrategy() {
		super(null);
	}

	public void setOperationResult(boolean value) {
		mOperationResult = value;
	}

	public void setEntity(Entity value) {
		mEntity = value;
	}

	@Override
	public boolean add(Entity value) {
		return mOperationResult;
	}

	@Override
	public boolean remove(Entity value) {
		return mOperationResult;
	}

	@Override
	public boolean update(Entity value) {
		return mOperationResult;
	}

	@Override
	public Line getLine(String name) {
		return mEntity == null ? null : (Line) mEntity;
	}

	@Override
	public Bus getBus(int id) {
		return mEntity == null ? null : (Bus) mEntity;
	}

	@Override
	public Station getStation(String name) {
		return mEntity == null ? null : (Station) mEntity;
	}
}