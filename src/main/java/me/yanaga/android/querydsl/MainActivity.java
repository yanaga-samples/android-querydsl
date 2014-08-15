package me.yanaga.android.querydsl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.common.base.Joiner;
import me.yanaga.android.querydsl.model.bean.Person;
import me.yanaga.android.querydsl.model.bean.PersonRepository;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {

	private Button updateButton;

	private Button insertButton;

	private TextView outputTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.insertButton = (Button) findViewById(R.id.insertButton);
		this.updateButton = (Button) findViewById(R.id.refreshButton);
		this.outputTextView = (TextView) findViewById(R.id.outputTextView);
		this.insertButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Person person = new Person();
				person.setName("Yanaga");
				PersonRepository.getInstance(MainActivity.this).save(person);
				outputTextView.setText("Successfully saved.");
			}
		});
		this.updateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				List<Person> people = PersonRepository.getInstance(MainActivity.this).findAll();
				List<String> names = new LinkedList<String>();
				for (Person pessoa : people) {
					names.add(pessoa.getName());
				}
				outputTextView.setText(Joiner.on(" | ").join(names));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(me.yanaga.android.querydsl.R.menu.main, menu);
		return true;
	}

}

