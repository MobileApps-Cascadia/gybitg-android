package edu.cascadia.mobas.gybitg;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.cascadia.mobas.gybitg.models.StatEntity;

import static edu.cascadia.mobas.gybitg.utilities.Constants.STAT_ID_KEY;

public class StatHistoryAdapter extends RecyclerView.Adapter<StatHistoryAdapter.ViewHolder> {
    private List<StatEntity> mStats;
    private Context mContext;

    public StatHistoryAdapter(List<StatEntity> mStats, Context context) {
        this.mStats = mStats;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stat_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StatEntity stat = mStats.get(position);
        holder.mTextView.setText("Game #" + stat.getId() + " on " + stat.getDateOfEntry());

        holder.mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editStat = new Intent(mContext, EditStatActivity.class);
                editStat.putExtra(STAT_ID_KEY, stat.getId());
                //mContext.startActivity(editStat);
            }
        });

    }

        @Override
    public int getItemCount() { return mStats.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        FloatingActionButton mFab;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.stat_text);
            mFab = itemView.findViewById(R.id.fab_stat);
        }
    }
}
