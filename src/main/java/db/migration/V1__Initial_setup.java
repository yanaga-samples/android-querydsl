package db.migration;

import org.flywaydb.core.api.migration.MigrationChecksumProvider;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

public class V1__Initial_setup implements JdbcMigration, MigrationChecksumProvider {

	@Override
	public void migrate(Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE PERSON (ID INT PRIMARY KEY, NAME VARCHAR(40))");
	}

	@Override
	public Integer getChecksum() {
		return 1;
	}

}
