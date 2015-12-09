package com.indyvision.tutorialfragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    public static final String KEY_VALUE = "KEY_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout(savedInstanceState);
    }

    protected void initLayout(Bundle savedInstanceState) {
        if (findViewById(R.id.activity_main_fragment_container) != null) {
            //we don't want to get overlapping fragments
            if (savedInstanceState != null) {

                manageScreenOrientation();

            } else {
                manageScreenOrientation();

//                PortraitFragment portraitFragment = new PortraitFragment();
//                portraitFragment.setArguments(getIntent().getExtras());
//
//                getSupportFragmentManager().beginTransaction().
//                        add(R.id.activity_main_fragment_container, portraitFragment).commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void manageScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            switchToPortrait();
        } else {
            switchToLandscape();
        }
    }

    private void switchToPortrait() {

        PortraitFragment portraitFragment = new PortraitFragment();
        Bundle args = new Bundle();
        args.putString(KEY_VALUE, "I'm in portrait, yaaaay!!!");
        portraitFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, portraitFragment)
//                .addToBackStack(null)
                .commit();
    }

    private void switchToLandscape() {

        LandscapeFragment landscapeFragment = new LandscapeFragment();
        Bundle args = new Bundle();
        args.putString(KEY_VALUE, "I'm in landscape, yaaaay!!!");
        landscapeFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, landscapeFragment)
//                .addToBackStack(null)
                .commit();
    }
}
