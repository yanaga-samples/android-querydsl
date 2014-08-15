package me.yanaga.android.querydsl.repository;

import java.sql.Connection;

public interface ConnectionCallback<T> {

	public T executeAndReturn(Connection conn);

}
