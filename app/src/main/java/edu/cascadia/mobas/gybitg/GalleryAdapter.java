//Will have a list of Uris of the videos
package edu.cascadia.mobas.gybitg;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private List<Uri> videoPaths;
    private  Context mContext;

    public GalleryAdapter(List<Uri> videoPaths, Context mContext) {
        this.videoPaths = videoPaths;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       // LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
       // View view = inflater.inflate(R.layout.gallery_list_item, viewGroup, false);
       // ViewHolder myViewHolder = new ViewHolder(view);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_list_item,viewGroup,false);
        return  (new ViewHolder(view));//myViewHolder;
    }

    //called each time a row is refreshed with data object
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //get a reference to a string that is the string in the list recieved
        //To get the newest video, get the position of the last element in the list
        //(videoPaths.size() - position)- 1 Instead of videoPaths.get(position);
        Uri path = videoPaths.get(position);
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(path.getPath(),
                MediaStore.Video.Thumbnails.MINI_KIND);
        viewHolder.videoTitle.setText(path.getLastPathSegment());
        viewHolder.thumbnail.setImageBitmap(thumb);
        viewHolder.thumbnail.setVisibility(View.VISIBLE);

    }

    //returns the size of the list of video paths
    @Override
    public int getItemCount() {
        return videoPaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton thumbnail;
        TextView videoTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.video1_imageButton);
            videoTitle = itemView.findViewById(R.id.video1_tumbnail_title);
        }
    }



  /**  public void getthumbnailBitmap(Uri mUri){

        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(mUri.getPath(),
                MediaStore.Video.Thumbnails.MINI_KIND);
        //ImageView v = (ImageView) findViewById(R.id.video1_thumbnail);
        ImageButton vIB = findViewById(R.id.video1_imageButton);
        TextView mtextView = (TextView) findViewById(R.id.video1_tumbnail_title);
        vIB.setImageBitmap(thumb);
        vIB.setVisibility(View.VISIBLE);
        // v.setImageBitmap(thumb);
        mtextView.setText("Your video 1");
    }*/
}
