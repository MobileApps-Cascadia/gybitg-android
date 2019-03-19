
//Will have a list of VideoUpload
//Will return the size of the list of the videolist
//Will have a nested class ViewHolder to manage the view itself
// and Will display the data in the view passed in the ViewHolder constructor
//Has a onbindViewHolder method that is called each time a row is refreshed with data object
//get a reference to the VideoUpload that is equal to the one at this position
//sets the text of the GalleryText to the title
//gets the path of the VideoUpload entity and creates a thumbnail with the path of the video
//scales the thumbnail to width and height in pixels and if a filter should be used 1280 720 youtube uses
//Will return the size of the list of the videolist
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

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder>{
    private List<VideoUpload> videolist;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public GalleryAdapter(List<VideoUpload> mVideoUploads, Context context) {
        this.videolist = mVideoUploads;
        mContext = context;
        notifyDataSetChanged();
    }

    //Inflates the layout of the videolistrow
    // Declares and Initializes the imageButton and setsOnclickListener to do something
    //Returns the viewHolder with the view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_row,parent,false);
        //set the onClick for the imageButton
        final ImageButton  mImageButton = itemView.findViewById(R.id.thumbnail);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                // mImageButton.setVisibility(View.INVISIBLE);
            }
        });

        return  new GalleryAdapter.MyViewHolder(itemView);

    }

    //called each time a row is refreshed with data object
    //get a reference to the VideoUpload that is equal to the one at this position
    //sets the text of the GalleryText to the title
    //gets the path of the VideoUpload entity and creates a thumbnail with the path of the video
    //scales the thumbnail to width and height in pixels and if a filter should be used 1280 720 youtube uses
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

        VideoUpload videolisting = videolist.get(position);
        viewHolder.GalleryText.setText(videolisting.GetTitle());
        //call nextLine when Url setup for video uploading
        //holder.GalleryImage.setImageResource(videolisting.GetImageURL());
        Uri path = videolisting.getmUri();
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(path.getPath(), MediaStore.Video.Thumbnails.MINI_KIND);
        // viewHolder.videoTitle.setText(path.getLastPathSegment()); to set the title to the name of the videofile
        viewHolder.GalleryText.setText("Video Title");
        Bitmap scaled = Bitmap.createScaledBitmap(thumb,1280,720, true);
        viewHolder.GalleryImage.setImageBitmap(scaled);
        viewHolder.GalleryText.setVisibility(View.VISIBLE);
    }

    //Purpose: to return the size of the list of the videolist
    //Precondition: none
    //Postcondtion: The list size will be returned as an int
    @Override
    public int getItemCount() {
        return videolist.size();
    }


    //set the list of the videolist
    //notifies the adapter of a change
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
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton GalleryImage;
        TextView GalleryText;

        public MyViewHolder(@NonNull View v) {
            super(v);
            GalleryImage = v.findViewById(R.id.thumbnail);
            GalleryText = v.findViewById(R.id.title);
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
