package edu.cascadia.mobas.gybitg;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import edu.cascadia.mobas.gybitg.models.StatEntity;
import edu.cascadia.mobas.gybitg.viewmodel.EditStatViewModel;

import static edu.cascadia.mobas.gybitg.utilities.Constants.TEMP_USER_ID;
import static edu.cascadia.mobas.gybitg.utilities.Constants.EDITING_KEY;
import static edu.cascadia.mobas.gybitg.utilities.Constants.STAT_ID_KEY;

public class EditStatActivity extends AppCompatActivity {

    private EditStatViewModel mViewModel;
    private Boolean mNewStat, mEditing;

    private EditText ePoints, eRebounds, eAssists, eSteals, eBlocks, eMinutesPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ePoints = findViewById(R.id.editText_points);
        eRebounds = findViewById(R.id.editText_rebounds);
        eAssists = findViewById(R.id.editText_assists);
        eSteals = findViewById(R.id.editText_steals);
        eBlocks = findViewById(R.id.editText_blocks);
        eMinutesPlayed = findViewById(R.id.editText_minutesPlayed);

        if(savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initViewModel();

        FloatingActionButton fab = findViewById(R.id.fab_save_game);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrUpdate();
            }
        });
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EditStatViewModel.class);
        mViewModel.mLiveStat.observe(this, new Observer<StatEntity>() {
            @Override
            public void onChanged(@Nullable StatEntity statEntity) {
                if (statEntity != null && !mEditing) {
                    ePoints.setText(Integer.toString(statEntity.getPoints()));
                    eRebounds.setText(Integer.toString(statEntity.getRebounds()));
                    eAssists.setText(Integer.toString(statEntity.getAssists()));
                    eSteals.setText(Integer.toString(statEntity.getSteals()));
                    eBlocks.setText(Integer.toString(statEntity.getBlocks()));
                    eMinutesPlayed.setText(Integer.toString(statEntity.getMinutesPlayed()));
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(R.string.new_game_stat);
            mNewStat = true;
        } else {
            setTitle(R.string.edit_game_stat);
            int statId = extras.getInt(STAT_ID_KEY);
            mEditing = extras.getBoolean("STAT_EDITING");
            mViewModel.loadData(statId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewStat) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_edit, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveOrUpdate();
            return true;
        }
        else if (item.getItemId() == R.id.action_delete) {
            mViewModel.deleteStat();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveOrUpdate() {
        if(TextUtils.isEmpty(ePoints.getText().toString().trim()) || TextUtils.isEmpty(eRebounds.getText().toString().trim())
            || TextUtils.isEmpty(eAssists.getText().toString().trim()) || TextUtils.isEmpty(eSteals.getText().toString().trim())
            || TextUtils.isEmpty(eBlocks.getText().toString().trim()) || TextUtils.isEmpty(eMinutesPlayed.getText().toString().trim())){
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return;
        }
        mViewModel.saveOrUpdate(
                Integer.parseInt(ePoints.getText().toString()),
                Integer.parseInt(eRebounds.getText().toString()),
                Integer.parseInt(eAssists.getText().toString()),
                Integer.parseInt(eSteals.getText().toString()),
                Integer.parseInt(eBlocks.getText().toString()),
                Integer.parseInt(eMinutesPlayed.getText().toString())
        );
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}
