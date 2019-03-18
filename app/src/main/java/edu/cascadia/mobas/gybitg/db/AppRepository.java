package edu.cascadia.mobas.gybitg.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobas.gybitg.models.StatEntity;
import edu.cascadia.mobas.gybitg.utilities.SampleData;

import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;


public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<StatEntity>> mStats;
    private gybitgDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = gybitgDatabase.getDatabase(context);
    }

    public LiveData<List<StatEntity>> getAllStatsByUserId(String userId) {
        return mDb.statDao().getAllStatsByUserId(userId);
    }
    public List<Integer> getPointsByUserId(String userId) {
        return mDb.statDao().getPointsByUserId(userId);
    }
    public List<Integer> getReboundsByUserId(String userId) {
        return  mDb.statDao().getReboundsByUserId(userId);
    }
    public List<Integer> getAssistsByUserId(String userId) {
        return mDb.statDao().getAssistsByUserId(userId);
    }
    public List<Integer> getStealsByUserId(String userId) {
        return mDb.statDao().getStealsByUserId(userId);
    }
    public List<Integer> getBlocksByUserId(String userId) {
        return mDb.statDao().getBlocksByUserId(userId);
    }
    public List<Integer> getMinutesPlayedByUserId(String userId) {
        return mDb.statDao().getMinutesPlayedByUserId(userId);
    }
}
