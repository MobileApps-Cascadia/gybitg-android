package edu.cascadia.mobas.gybitg;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.gybitg.models.StatEntity;
import edu.cascadia.mobas.gybitg.viewmodel.StatHistoryViewModel;

public class StatHistoryFragment extends Fragment {

    private StatHistoryViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private StatHistoryAdapter mStatsAdapter;
    private List<StatEntity> statsData = new ArrayList<>();

    public static StatHistoryFragment newInstance() {
        return new StatHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // initialize the view with the stat_history_fragment layout
        View rootView = inflater.inflate(R.layout.stat_history_fragment, container, false);

        // get reference to recycler view
        mRecyclerView = rootView.findViewById(R.id.stat_recycler_view);

        initRecyclerView();

        initViewModel();

        // setup the floating action button
        FloatingActionButton fab =rootView.findViewById(R.id.fab_stat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent editStat = new Intent(getActivity(), EditStatActivity.class);
                startActivity(editStat);
            }
        });

        return rootView;
    }

    private void initViewModel() {

        // add an observable to the stat entity
        final Observer<List<StatEntity>> statsObserver = new Observer<List<StatEntity>>() {
            @Override
            public void onChanged(@NonNull List<StatEntity> statEntities) {
                statsData.clear();
                statsData.addAll(statEntities);
                if (mStatsAdapter == null) {
                    mStatsAdapter = new StatHistoryAdapter(statsData, getContext());
                    mRecyclerView.setAdapter(mStatsAdapter);
                }
                else {
                    mStatsAdapter.notifyDataSetChanged();
                }

            }
        };

        mViewModel = ViewModelProviders.of(this).get(StatHistoryViewModel.class);
        mViewModel.mStats.observe(this, statsObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // set the recycler view layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        // add a divider bar between items in recycler view
        DividerItemDecoration divider= new DividerItemDecoration(
            mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(divider);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



}
