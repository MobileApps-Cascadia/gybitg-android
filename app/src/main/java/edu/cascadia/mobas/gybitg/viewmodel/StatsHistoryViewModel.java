package edu.cascadia.mobas.gybitg.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import edu.cascadia.mobas.gybitg.db.AppRepository;
import edu.cascadia.mobas.gybitg.models.StatEntity;


public class StatsHistoryViewModel extends AndroidViewModel {

    public LiveData<List<StatEntity>> mStats;
    private AppRepository mRepository;

    public StatsHistoryViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mStats = mRepository.mStats;
    }
}
