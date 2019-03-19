package edu.cascadia.mobas.gybitg.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobas.gybitg.db.AppRepository;
import edu.cascadia.mobas.gybitg.models.StatEntity;

import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;

public class EditStatViewModel extends AndroidViewModel {

    public MutableLiveData<StatEntity> mLiveStat = new MutableLiveData<>();

    // Reference to the repository
    private AppRepository mRepository;

    // setup thread executor reference
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditStatViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void loadData(final int statId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                StatEntity stat = mRepository.getStatById(statId);
                mLiveStat.postValue(stat);
            }
        });
    }

    public void saveOrUpdate(int mPoints, int mRebounds, int mAssists, int mSteals, int mBlocks, int mMinutesPlayed) {
        StatEntity stat = mLiveStat.getValue();

        if (stat == null) {
            Date mDate = new Date();
            stat = new StatEntity(TEMP_USER_ID,mDate,mPoints,mRebounds,mAssists,mSteals,mBlocks,mMinutesPlayed);
        } else {
            stat.setPoints(mPoints);
            stat.setRebounds(mRebounds);
            stat.setAssists(mAssists);
            stat.setSteals(mSteals);
            stat.setBlocks(mBlocks);
            stat.setMinutesPlayed(mMinutesPlayed);
        }
        mRepository.insertStat(stat);
    }

    public void deleteStat() { mRepository.deleteStat(mLiveStat.getValue()); }
}
