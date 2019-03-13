package edu.cascadia.mobas.gybitg.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobas.gybitg.models.StatEntity;
import edu.cascadia.mobas.gybitg.utilities.SampleData;

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
        mStats = getAllStats();
    }

    private LiveData<List<StatEntity>> getAllStats() {
        return mDb.statDao().getAllStats();
    }


}
