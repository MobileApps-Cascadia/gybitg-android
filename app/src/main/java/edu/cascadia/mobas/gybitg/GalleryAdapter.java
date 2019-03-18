
//Will have a list of VideoUpload
//Will return the size of the list of the videolist
//Will have a nested class ViewHolder to manage the view itself
// and Will display the data in the view passed in the ViewHolder constructor
package edu.cascadia.mobas.gybitg;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.cascadia.mobas.gybitg.viewmodel.GalleryViewModel;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private List<VideoUpload> videolist;
    private Context mContext;

    public GalleryAdapter(List<VideoUpload> mVideoUploads, Context context) {
        this.videolist = mVideoUploads;
        mContext = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        // LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // View view = inflater.inflate(R.layout.gallery_list_item, viewGroup, false);
        // ViewHolder myViewHolder = new ViewHolder(view);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_row,viewGroup,false);
        //set the onClick for the imageButton
        final ImageButton  mImageButton = view.findViewById(R.id.thumbnail);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                // mImageButton.setVisibility(View.INVISIBLE);
            }
        });

        return  (new ViewHolder(view));//myViewHolder;

    }


    //called each time a row is refreshed with data object
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //get a reference to a string that is the string in the list recieved
        VideoUpload videolisting = videolist.get(position);
        viewHolder.videoTitle.setText(videolisting.GetTitle());
        //holder.GalleryImage.setImageResource(videolisting.GetImageURL());
        //get the path of the VideoUpload entity
       /**Uri path = videolisting.getmUri();
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(path.getPath(), MediaStore.Video.Thumbnails.MINI_KIND);
        // viewHolder.videoTitle.setText(path.getLastPathSegment());
        viewHolder.videoTitle.setText("Video Title");
        //scales the thumbnail to width and height in pixels and if a filter should be used
        Bitmap scaled = Bitmap.createScaledBitmap(thumb,1280,720, true);
        viewHolder.thumbnail.setImageBitmap(scaled);*/
        viewHolder.thumbnail.setVisibility(View.VISIBLE);
    }

    //Purpose: to return the size of the list of the videolist
    //Precondition: none
    //Postcondtion: The list size will be returned as an int
    @Override
    public int getItemCount() {
        return videolist.size();
    }


    //set the list
    public void setVideolist(List<VideoUpload> videoUploads){
        videolist = videoUploads;
        notifyDataSetChanged();
    }

    //Purpose: The purpose of nested class viewHolder is to manage the view itself
    //it has to contain references to any compontents that you want to display data
    //Precondition: Constructor parameter is non null
    //Postcondtion: Will display the data in the view passed in the constructor
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton thumbnail;
        TextView videoTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            videoTitle = itemView.findViewById(R.id.title);
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


/**public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {
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
 }*/
    // Provide a suitable constructor (depends on the kind of dataset)
    /**  public GalleryAdapter(List<VideoUpload> videolist) {
     this.videolist = videolist;
     }*/

    /**  @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_row, parent, false);
    return new GalleryAdapter.MyViewHolder(itemView);
    }*/

   /* @Override
    public void onBindViewHolder (MyViewHolder holder, int position){
        VideoUpload videolisting = videolist.get(position);
        holder.GalleryText.setText(videolisting.GetTitle());
        holder.GalleryImage.setImageResource(videolisting.GetImageURL());
    }*/

    /** @Override
    public int getItemCount(){
    return videolist.size();
    }*/

}
