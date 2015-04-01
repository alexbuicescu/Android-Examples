package example.com.databaseapplication;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    ListView awesomeListView;
    ListViewAdapter listViewAdapter;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();
        initListView();
    }

    private void initDB() {
        dbHelper = new DatabaseHelper(this);

        if (dbHelper.numberOfRows() == 0) {
            for (int i = 0; i < 10; i++) {
                Product product = new Product("Product " + i, i,
                        BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                dbHelper.insertProduct(product);
            }
        }
    }

    private void initListView() {
        awesomeListView = (ListView) findViewById(R.id.awesome_list_view);
        listViewAdapter = new ListViewAdapter(this, dbHelper.getAllProducts());
        awesomeListView.setAdapter(listViewAdapter);
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
