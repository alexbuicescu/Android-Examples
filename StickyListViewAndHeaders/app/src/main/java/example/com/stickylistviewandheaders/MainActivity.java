package example.com.stickylistviewandheaders;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class MainActivity extends ActionBarActivity {

    StickyListHeadersListView awesomeListView;
    ListViewAdapter listViewAdapter;

    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> headers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItemsAndHeaders();

        awesomeListView = (StickyListHeadersListView) findViewById(R.id.awesome_list_view);
        awesomeListView.setFastScrollEnabled(true);
        listViewAdapter = new ListViewAdapter(this, items);
        awesomeListView.setAdapter(listViewAdapter);
    }

    private void initItemsAndHeaders() {
        for (int i = 'A'; i <= 'Z'; i++) {
            for (int j = 0; j < 3; j++) {
                items.add((char) i + " item " + j);
                headers.add((char) i + "");
            }
        }

//        for(int i = 0; i < 3; i++)
//        {
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
