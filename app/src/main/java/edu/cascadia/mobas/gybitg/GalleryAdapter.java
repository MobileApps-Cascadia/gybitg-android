package edu.cascadia.mobas.gybitg;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {
    private List<VideoUpload> videolist;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView GalleryText;
        public ImageView GalleryImage;
        public MyViewHolder(View v) {
            super(v);
            GalleryText = v.findViewById(R.id.title);
            GalleryImage = v.findViewById(R.id.thumbnail);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public GalleryAdapter(List<VideoUpload> videolist) {
            this.videolist = videolist;
    }

    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_row, parent, false);
        return new GalleryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position){
        VideoUpload videolisting = videolist.get(position);
        holder.GalleryText.setText(videolisting.GetTitle());
        holder.GalleryImage.setImageResource(videolisting.GetImageURL());
    }

    @Override
    public int getItemCount(){
        return videolist.size();
    }
}
