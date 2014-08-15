package me.yanaga.android.querydsl.repository;

import android.content.Context;
import org.sqldroid.SQLDroidConnection;
import org.sqldroid.SQLDroidDriver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class AbstractRepository {

	private final String url;

	protected AbstractRepository(Context context) {
		this.url = Database.getConnectionUrl(context);
	}

	protected <T> T execute(ConnectionCallback<T> callback) {
		Connection connection = null;
		try {
			connection = getConnection();
			return callback.executeAndReturn(connection);
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Connection getConnection() {
		Properties properties = new Properties();
		properties.put(SQLDroidDriver.ADDITONAL_DATABASE_FLAGS,
				android.database.sqlite.SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		try {
			return new SQLDroidConnection(url, properties);
		}
		catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

}
