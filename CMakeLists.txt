cmake_minimum_required(VERSION 3.5)
project(bus_management)

# Get the entry point from the user
SET(APP_ENTRY_POINT CACHE STRING "Specify the application's entry point")

# Include Java to CMake
find_package(Java REQUIRED)
include(UseJava)

set(SOURCE_FILES 
	src/main/java/db/DatabaseFacade.java
	src/main/java/db/auth/AccountType.java
	src/main/java/db/auth/Authenticator.java
	src/main/java/db/entities/Bus.java
	src/main/java/db/entities/Entity.java
	src/main/java/db/entities/Line.java
	src/main/java/db/entities/Station.java
	src/main/java/db/strategies/AbstractDatabaseStrategy.java
	src/main/java/db/strategies/IDatabaseStrategy.java
	src/main/java/db/strategies/ManagerStrategy.java
	src/main/java/db/strategies/UserStrategy.java
)

if(${UNIT_TESTS})
	# Generate the manifest before building the application
	add_custom_target(manifest ALL
		python "${PROJECT_SOURCE_DIR}/scripts/generate_manifest.py" 
				"${CMAKE_CURRENT_BINARY_DIR}/manifest.txt" 
				"$ENV{CLASSPATH}"
				"${APP_ENTRY_POINT}"
	)

	add_jar(test_${CMAKE_PROJECT_NAME} 		
		${SOURCE_FILES}
		
		src/tests/TestRunner.java
		src/tests/db/TestDBSuite.java
		src/tests/db/TestDatabaseFacade.java
		src/tests/db/entities/TestEntity.java

		src/tests/fakes/db/FakeDatabaseFacade.java
		src/tests/fakes/db/auth/FakeAuthenticator.java
		src/tests/fakes/db/entities/FakeEntity.java
		src/tests/fakes/db/strategies/FakeManagerStrategy.java

		MANIFEST ${CMAKE_CURRENT_BINARY_DIR}/manifest.txt

		INCLUDE_JARS
		$ENV{CLASSPATH}
	)
else()
	add_jar(${CMAKE_PROJECT_NAME} 
		${SOURCE_FILES}
		src/main/java/Application.java

		ENTRY_POINT ${APP_ENTRY_POINT}
	)
endif()
