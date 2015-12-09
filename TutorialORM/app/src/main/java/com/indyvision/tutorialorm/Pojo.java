package com.indyvision.tutorialorm;

import com.orm.SugarRecord;

/**
 * Created by alexbuicescu on 09.12.2015.
 */
public class Pojo extends SugarRecord<Pojo> {

    private int valueInt;
    private String valueString;

    public Pojo()
    {

    }

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}
