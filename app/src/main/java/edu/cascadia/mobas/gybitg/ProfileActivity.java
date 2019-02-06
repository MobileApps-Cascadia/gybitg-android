package edu.cascadia.mobas.gybitg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.cascadia.mobas.gybitg.R;
import edu.cascadia.mobas.gybitg.db.StatDao;
import edu.cascadia.mobas.gybitg.db.gybitgDatabase;

public class ProfileActivity extends AppCompatActivity {

    StatDao statDao;

    public static String TEMP_USER_ID = "ksmith0372";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // grab the Stat Data Access Object
        statDao = gybitgDatabase.getDatabase(this).statDao();

        // setup reference to Add Game button
        final Button btn_add_game = findViewById(R.id.add_game);

        loadStats();

        // set up OnClick listener for Add Game/Stat button
        btn_add_game.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // set up reference to new Intent
                        Intent stats_page = new Intent(ProfileActivity.this, StatsActivity.class);
                        startActivity(stats_page);
                    }
                });

    }
    public void loadStats() {
        // reference to stats section elements
        TextView mPointsValueTextView = findViewById(R.id.pointsValuetxt);
        TextView mReboundsValueTextView = findViewById(R.id.reboundsValuetxt);
        TextView mAssistsValueTextView = findViewById(R.id.assistsValuetxt);
        TextView mStealsValueTextView = findViewById(R.id.stealsValuetxt);
        TextView mBlocksValueTextView = findViewById(R.id.blocksValuetxt);
        TextView mMinutesPlayedValueTextView = findViewById(R.id.minutesPlayedValuetxt);

        // use the dao to find the total number of games the user has stored in the db
        float totalGames = statDao.getStatsByUserId(TEMP_USER_ID).size();

        // create references for the all the stats that will be displayed
        float ppg, rpg, spg, apg, bpg, mpg;
        float totalPoints=0, totalRebounds=0, totalSteals=0, totalAssists=0, totalBlocks=0, totalMinutesPlayed=0;

        // this loop runs through each stat entry and totals the points
        for (int tPoints:statDao.getPointsByUserId(TEMP_USER_ID)) {
            totalPoints += tPoints;
        }
        // calculate the average points per game
        ppg = (totalPoints/totalGames);

        // this loop runs through each stat entry and totals the rebounds
        for (int tRebounds:statDao.getReboundsByUserId(TEMP_USER_ID)) {
            totalRebounds += tRebounds;
        }
        // calculate the average rebounds per game
        rpg = (totalRebounds/totalGames);

        // this loop runs through each stat entry and totals the assists
        for (int tAssists:statDao.getAssistsByUserId(TEMP_USER_ID)) {
            totalAssists += tAssists;
        }
        // calculate the average assists per game
        apg = (totalAssists/totalGames);

        // this loop runs through each stat entry and totals the steals
        for (int tSteals:statDao.getStealsByUserId(TEMP_USER_ID)) {
            totalSteals += tSteals;
        }
        // calculate the average steals per game
        spg = (totalSteals/totalGames);

        // this loop runs through each stat entry and totals the blocks
        for (int tBlocks:statDao.getBlocksByUserId(TEMP_USER_ID)) {
            totalBlocks += tBlocks;
        }
        // calculate the average blocks per game
        bpg = (totalBlocks/totalGames);

        // this loop runs through each stat entry and totals the minutes played
        for (int tMinutesPlayed:statDao.getMinutesPlayedByUserId(TEMP_USER_ID)) {
            totalMinutesPlayed += tMinutesPlayed;
        }
        // calculate the average minutes played per game
        mpg = (totalMinutesPlayed/totalGames);

        // display the points/rebounds/steals/assists/blocks/minutes played per game,
        // in the profile text view under the stats section on user profile
        mPointsValueTextView.setText(String.format("%.01f",ppg));
        mReboundsValueTextView.setText(String.format("%.01f",rpg));
        mAssistsValueTextView.setText(String.format("%.01f",apg));
        mStealsValueTextView.setText(String.format("%.01f",spg));
        mBlocksValueTextView.setText(String.format("%.01f",bpg));
        mMinutesPlayedValueTextView.setText(String.format("%.01f",mpg));

    }
}
