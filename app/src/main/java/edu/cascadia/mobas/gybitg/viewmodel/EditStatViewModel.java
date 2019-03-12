package edu.cascadia.mobas.gybitg.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import edu.cascadia.mobas.gybitg.models.StatEntity;

public class EditStatViewModel extends AndroidViewModel {

    public MutableLiveData<StatEntity> mLiveStat = new MutableLiveData<>();


    public EditStatViewModel(@NonNull Application application) {
        super(application);
    }
}
