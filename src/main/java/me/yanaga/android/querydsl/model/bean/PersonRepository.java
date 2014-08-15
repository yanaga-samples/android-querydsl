package me.yanaga.android.querydsl.model.bean;

import android.content.Context;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLiteTemplates;
import com.mysema.query.sql.dml.SQLInsertClause;
import me.yanaga.android.querydsl.model.QPerson;
import me.yanaga.android.querydsl.repository.AbstractRepository;
import me.yanaga.android.querydsl.repository.ConnectionCallback;
import me.yanaga.android.querydsl.repository.ConnectionCallbackWithoutResult;

import java.sql.Connection;
import java.util.List;

public class PersonRepository extends AbstractRepository {

	private static PersonRepository INSTANCE;

	private PersonRepository(Context context) {
		super(context);
	}

	public static PersonRepository getInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = new PersonRepository(context);
		}
		return INSTANCE;
	}

	public List<Person> findAll() {
		return execute(new ConnectionCallback<List<Person>>() {
			@Override
			public List<Person> executeAndReturn(Connection conn) {
				QPerson qPerson = QPerson.person;
				SQLQuery query = new SQLQuery(conn, SQLiteTemplates.DEFAULT);
				return query.from(qPerson).where(qPerson.name.containsIgnoreCase("a"))
						.list(qPerson);
			}
		});
	}

	public void save(final Person person) {
		execute(new ConnectionCallbackWithoutResult() {
			@Override
			public void executeWithoutResult(Connection conn) {
				QPerson qPerson = QPerson.person;
				new SQLInsertClause(conn, SQLiteTemplates.DEFAULT, qPerson)
						.populate(person).execute();
			}
		});
	}

}

