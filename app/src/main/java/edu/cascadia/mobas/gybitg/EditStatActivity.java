package edu.cascadia.mobas.gybitg;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import edu.cascadia.mobas.gybitg.db.StatDao;
import edu.cascadia.mobas.gybitg.db.gybitgDatabase;
import edu.cascadia.mobas.gybitg.models.StatEntity;

import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;

public class EditStatActivity extends AppCompatActivity {

    private Intent mProfilePage;
    private StatDao statDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Initialize the Intent
        mProfilePage = new Intent(this, ProfileActivity.class);



    }
}
