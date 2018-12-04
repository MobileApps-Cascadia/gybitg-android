package edu.cascadia.mobas.gybitg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.cascadia.mobas.gybitg.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // setup reference to Add Game button
        final Button btn_add_game = findViewById(R.id.add_game);

        // set up reference to new Intent
        final Intent stats_page = new Intent(this, StatsActivity.class);

        // set up OnClick listener for Add Game button
        btn_add_game.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(stats_page);
                    }
                });

    }
}
