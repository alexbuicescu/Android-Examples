package com.indyvision.tutorialorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
    }

    private void initDB()
    {
        Pojo pojo1 = new Pojo();
        pojo1.setValueInt(0);
        pojo1.setValueString("0");
        pojo1.save();

        Pojo pojo2 = new Pojo();
        pojo2.setValueInt(1);
        pojo2.setValueString("1");
        pojo2.save();

        List<Pojo> pojoList = Pojo.find(Pojo.class, "value_int >= ?", "0");

        for (Pojo pojo :
                pojoList) {
            Log.i("database test", pojo.getValueString());
        }
    }
}
