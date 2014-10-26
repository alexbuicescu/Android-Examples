package com.example.alexandru.examplelistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ActionBarActivity {

	private ListView listViewList;
	private ListViewAdapter listViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		supportRequestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my);

		setUpListView();
	}

	private void setUpListView() {
		listViewList = (ListView) findViewById(R.id.listview_list);

		listViewAdapter = new ListViewAdapter(this, getListItems());

		listViewList.setAdapter(listViewAdapter);
		listViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Toast.makeText(MyActivity.this, "Clicked on '" + getListItems().get(i) + "'", Toast.LENGTH_LONG).show();
			}
		});
	}

	private List<String> getListItems() {
		List<String> newList = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			newList.add("Item #" + i);
		}

		return newList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
