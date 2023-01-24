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

public class ManagerStrategy extends UserStrategy {
	public ManagerStrategy(Connection connection) {
		super(connection);
	}

	@Override
	public boolean add(Entity value) {
		// TODO

		// FIXME : dummy implementation, remove
		if (mEntities.indexOf(value) != -1) {
			return false;
		}
		mEntities.add(value);

		return true;
	}

	@Override
	public boolean remove(Entity value) {
		// TODO

		// FIXME : dummy implementation, remove
		int index = mEntities.indexOf(value);
		if (index != -1) {
			mEntities.remove(index);
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Entity value) {
		for (int index = 0; index < mEntities.size(); ++index) {
			Entity entity = mEntities.get(index);
			if (entity.getClass() != value.getClass() || entity.getId() != value.getId()) {
				continue;
			}

			mEntities.set(index, value);
			return true;
		}

		return false;
	}
}