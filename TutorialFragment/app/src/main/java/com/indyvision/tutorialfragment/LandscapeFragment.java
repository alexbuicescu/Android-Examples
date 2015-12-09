package com.indyvision.tutorialfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * Created by alexbuicescu on 09.12.2015.
 */
public class LandscapeFragment extends Fragment {

    private String value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_landscape, container, false);

        Bundle args = getArguments();

        //don't use savedInstanceState - only for orientation changes
        if (args != null) {
            //get the saved stuff here

            value = args.getString(MainActivity.KEY_VALUE);
        }
        else
        {
            value = "empty instance state";
        }
        TextView infoTextView = (TextView) view.findViewById(R.id.fragment_landscape_info_textview);
        infoTextView.setText(value);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //can't do this here
//        TextView infoTextView = (TextView) getView().findViewById(R.id.fragment_portrait_info_textview);
//        infoTextView.setText(value);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //put some stuff in here
        //outState.putString("KEY_NAME", "value");
    }
}
