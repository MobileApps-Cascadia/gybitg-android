package edu.cascadia.mobas.gybitg;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import edu.cascadia.mobas.gybitg.R;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_form);

        // setup reference to Add Game button
        final Button btn_save = findViewById(R.id.save_game);

        // set up reference to new Intent
        final Intent profile_page = new Intent(this, ProfileActivity.class);

        // set up OnClick listener for Add Game button
        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(profile_page);
                    }
                });

        //get the spinner from the layout
        Spinner dropdownPoints = findViewById(R.id.spinnerPoints);
        Spinner dropdownRebounds = findViewById(R.id.spinnerRebounds);
        Spinner dropdownAssists = findViewById(R.id.spinnerAssists);
        Spinner dropdownSteals = findViewById(R.id.spinnerSteals);
        Spinner dropdownBlocks = findViewById(R.id.spinnerBlocks);
        Spinner dropdownTurnovers = findViewById(R.id.spinnerTurnovers);
        Spinner dropdownMinutesPlayed = findViewById(R.id.spinnerMinutesPlayed);
//create an array of integers for the spinner
        String[] items = new String[101];
        for (int i = 0; i < items.length; i++) {
                items[i] = Integer.toString(i);
        }

//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item , items);
//set the spinners adapter to the previously created one
        dropdownPoints.setAdapter(adapter);
        dropdownAssists.setAdapter(adapter);
        dropdownRebounds.setAdapter(adapter);
        dropdownSteals.setAdapter(adapter);
        dropdownBlocks.setAdapter(adapter);
        dropdownTurnovers.setAdapter(adapter);
        dropdownMinutesPlayed.setAdapter(adapter);
        //uses the spinner_dropdown_item.xml to set the background and text color of the items in the spinner
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdownPoints.setAdapter(adapter);
    }
}
