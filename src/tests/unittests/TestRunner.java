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
package unittests;

import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestDBSuite.class);

		List<Failure> failures = result.getFailures();
		if (!failures.isEmpty()) {
			System.out.println("");
			System.out.println("List of the failing tests :");
			for (Failure failure : failures) {
				Description description = failure.getDescription();
				System.out.println("-- " + description.getClassName() + "." + description.getMethodName());

				System.out.println(failure.getMessage() + "\n");
			}
		}

		System.out.println("");
		System.out.println("Failed " + result.getFailureCount() + " out of " + result.getRunCount() + " tests.");
	}
}