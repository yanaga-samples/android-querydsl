package me.yanaga.android.querydsl.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.android.ContextHolder;

public class Database {

	private static String url;

	public static String getConnectionUrl(Context context) {
		if (url == null) {
			SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("database", 0, null);
			url = String.format("jdbc:sqldroid:%s", sqLiteDatabase.getPath());
			ContextHolder.setContext(context);
			Flyway flyway = new Flyway();
			flyway.setInitOnMigrate(true);
			flyway.setDataSource(url, "", "");
			flyway.migrate();
			sqLiteDatabase.close();
		}
		return url;
	}

}
