package edu.cascadia.mobas.gybitg.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import edu.cascadia.mobas.gybitg.BR;
import edu.cascadia.mobas.gybitg.db.AppRepository;
import edu.cascadia.mobas.gybitg.models.StatEntity;
import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;

public class StatsFragmentViewModel extends BaseObservable {

    // Reference to the repository
    private AppRepository mRepository;

    // Properties that will be displayed in stats fragment layout
    private String points;
    private String rebounds;
    private String assists;
    private String blocks;
    private String steals;
    private String minutesPlayed;

    private float totalGames;

    // Constructor to initialize an instance of the repository
    public StatsFragmentViewModel(@NonNull Application application) {

        mRepository = AppRepository.getInstance(application.getApplicationContext());

    }

    // Bind the fragment_stats layout to the stat properties
    @Bindable
    public String getPoints() { return this.points; }
    @Bindable
    public String getRebounds() { return this.rebounds; }
    @Bindable
    public String getAssists() { return this.assists; }
    @Bindable
    public String getBlocks() { return this.blocks; }
    @Bindable
    public String getSteals() { return this.steals; }
    @Bindable
    public String getMinutesPlayed() { return this.minutesPlayed; }

    // Using the two-binding to display the stats in the fragment_stats layout
    public void setStats(String mPoints, String mRebounds, String mAssists,
                         String mBlocks, String mSteals, String mMinutesPlayed) {
        this.points = mPoints;
        this.rebounds = mRebounds;
        this.assists = mAssists;
        this.blocks = mBlocks;
        this.steals = mSteals;
        this.minutesPlayed = mMinutesPlayed;
        notifyPropertyChanged(BR.points);
        notifyPropertyChanged(BR.rebounds);
        notifyPropertyChanged(BR.assists);
        notifyPropertyChanged(BR.blocks);
        notifyPropertyChanged(BR.steals);
        notifyPropertyChanged(BR.minutesPlayed);
    }

    // Main method that calculates the users stat averages
    public void loadStats() {

        totalGames = 0;
        // create references for the all the stats that will be displayed
        float ppg, rpg, spg, apg, bpg, mpg;
        float totalPoints=0, totalRebounds=0, totalSteals=0, totalAssists=0, totalBlocks=0, totalMinutesPlayed=0;

        // loop through the users total points, rebounds, assists, steals, blocks, and minutes played and calculate averages
        for (int tp: mRepository.getPointsByUserId(TEMP_USER_ID)) {
            totalPoints += tp;
            totalGames++;
        }
        for (int tr: mRepository.getReboundsByUserId(TEMP_USER_ID)) {
            totalRebounds += tr;
        }
        for (int ta: mRepository.getAssistsByUserId(TEMP_USER_ID)) {
            totalAssists += ta;
        }
        for (int ts: mRepository.getStealsByUserId(TEMP_USER_ID)) {
            totalSteals += ts;
        }
        for (int tb: mRepository.getBlocksByUserId(TEMP_USER_ID)) {
            totalBlocks += tb;
        }
        for (int tmp: mRepository.getMinutesPlayedByUserId(TEMP_USER_ID)) {
            totalMinutesPlayed += tmp;
        }
        ppg = (totalPoints/totalGames);
        rpg = (totalRebounds/totalGames);
        apg = (totalAssists/totalGames);
        spg = (totalSteals/totalGames);
        bpg = (totalBlocks/totalGames);
        mpg = (totalMinutesPlayed/totalGames);

        // Call the setStats method that will update the view with new data
        setStats(String.format(Locale.ENGLISH,"%.01f",ppg), String.format(Locale.ENGLISH,"%.01f",rpg), String.format(Locale.ENGLISH,"%.01f",apg),
                String.format(Locale.ENGLISH,"%.01f",spg), String.format(Locale.ENGLISH,"%.01f",bpg), String.format(Locale.ENGLISH,"%.01f",mpg));
    }


}
