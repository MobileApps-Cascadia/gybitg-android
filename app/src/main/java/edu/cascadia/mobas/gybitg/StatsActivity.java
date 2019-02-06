package edu.cascadia.mobas.gybitg;

import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;

import edu.cascadia.mobas.gybitg.R;
import edu.cascadia.mobas.gybitg.db.StatDao;
import edu.cascadia.mobas.gybitg.models.Stat;
import edu.cascadia.mobas.gybitg.db.gybitgDatabase;

public class StatsActivity extends AppCompatActivity {

    private Intent mProfilePage;
    private StatDao statDao;
    public static String TEMP_USER_ID = "ksmith0372";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_form);

        // Get Stat Data Access Object
        statDao = gybitgDatabase.getDatabase(this).statDao();

        // Initialize the Stat Form elements
        final Button mSaveButton = findViewById(R.id.save_game);
        final Spinner mPoints = findViewById(R.id.spinnerPoints);
        final Spinner mRebounds = findViewById(R.id.spinnerRebounds);
        final Spinner mAssists = findViewById(R.id.spinnerAssists);
        final Spinner mSteals = findViewById(R.id.spinnerSteals);
        final Spinner mBlocks = findViewById(R.id.spinnerBlocks);
        final Spinner mMinutesPlayed = findViewById(R.id.spinnerMinutesPlayed);

        // Initialize the Intent
        mProfilePage = new Intent(this, ProfileActivity.class);

        //create an array of integers for the spinner
        String[] items = new String[101];
        for (int i = 0; i < items.length; i++) {
                items[i] = Integer.toString(i);
        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item , items);

        //set the spinners adapter to the previously created one
        mPoints.setAdapter(adapter);
        mAssists.setAdapter(adapter);
        mRebounds.setAdapter(adapter);
        mSteals.setAdapter(adapter);
        mBlocks.setAdapter(adapter);
        mMinutesPlayed.setAdapter(adapter);
        //uses the spinner_dropdown_item.xml to set the background and text color of the items in the spinner
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // set up OnClick listener for Add Game button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int points = Integer.parseInt(mPoints.getSelectedItem().toString());
                        int rebounds = Integer.parseInt(mRebounds.getSelectedItem().toString());
                        int assists = Integer.parseInt(mAssists.getSelectedItem().toString());
                        int steals = Integer.parseInt(mSteals.getSelectedItem().toString());
                        int blocks = Integer.parseInt(mBlocks.getSelectedItem().toString());
                        int minutesPlayed = Integer.parseInt(mMinutesPlayed.getSelectedItem().toString());

                        Stat newStat = new Stat();

                        newStat.setPoints(points);
                        newStat.setRebounds(rebounds);
                        newStat.setAssists(assists);
                        newStat.setSteals(steals);
                        newStat.setBlocks(blocks);
                        newStat.setMinutesPlayed(minutesPlayed);
                        newStat.setUserId(TEMP_USER_ID);
                        newStat.setDateOfEntry(new Date());

                        try {
                            statDao.insert(newStat);
                            setResult(RESULT_OK);
                            finish();
                            Toast.makeText(StatsActivity.this, "New Game Stat Added", Toast.LENGTH_SHORT).show();
                            startActivity(mProfilePage);
                        } catch (SQLiteConstraintException e) {
                            Toast.makeText(StatsActivity.this,"There was an error adding new game to database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
