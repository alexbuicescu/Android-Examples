package example.com.reminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getEvents();
    }

    private void getEvents()
    {
        try {
            Context context = createPackageContext("example.com.calendar", 0);
            SharedPreferences pref = context.getSharedPreferences("my.preferences", Context.CONTEXT_IGNORE_SECURITY);
            String event = pref.getString("events", "No event for you");

            Log.i("sharedPref event", event);

            //you can write in the shared pref of another app only if they created the prefs using Context.MODE_WORLD_WRITEABLE, otherwise this will raise an exception
            pref.edit().putString("events", "why no events for me? :(").commit();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
