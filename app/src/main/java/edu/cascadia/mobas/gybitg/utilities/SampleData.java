package edu.cascadia.mobas.gybitg.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cascadia.mobas.gybitg.models.StatEntity;

import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;

public class SampleData {

    public static List<StatEntity> getStats() {
        List<StatEntity> statList = new ArrayList<>();

        Date mDate = new Date();

        statList.add(new StatEntity(10, TEMP_USER_ID, mDate,28, 3, 7, 1, 3, 22));
        statList.add(new StatEntity(11, TEMP_USER_ID, mDate,18, 13, 4, 6, 2, 29));

        return  statList;
    }


}
