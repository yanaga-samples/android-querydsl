package me.yanaga.android.querydsl.repository;

import java.sql.Connection;

public abstract class ConnectionCallbackWithoutResult implements ConnectionCallback<Void> {

	@Override
	public Void executeAndReturn(Connection conn) {
		executeWithoutResult(conn);
		return null;
	}

	public abstract void executeWithoutResult(Connection conn);

}
