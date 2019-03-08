//This Class will show a list of the videos the user has in thumbnails
//Will have a add Video button that will open the camera app
//Camera app will take a video and play the video
//The video will be converted to a thumbnail and added to list
package edu.cascadia.mobas.gybitg;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
public class GalleryActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1991;
    private static final int READ_IMAGE_PERMISSION_REQUEST_CODE = 2000;
    private ImageView mImageView;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery2);
        mImageView = (ImageView) findViewById(R.id.video1_thumbnail);
        Button addVideoBtn = (Button) findViewById(R.id.add_video);
        //if the device does not have a camera disable the button
        if(!hasCamera()){
            addVideoBtn.setEnabled(false);
        }
       
        addVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
                dispatchTakeVideoIntent();

            }
        });

    }

    //Creates a new intent to open the camera app
    //Creates the file to save the video using a Simpledate for each video to be unique

    private void dispatchTakeVideoIntent() {

        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {

            //create the file to save the video
            // set the image file name
            //will be stored in a file in the storage camera folder if the second parameter not
            String fileName = new SimpleDateFormat("yyyyMMddhhmm'.mp4'").format(new Date());
            File mediaFile = new
                    File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/myvideo" + fileName);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            Uri mvideoUri = Uri.fromFile(mediaFile);
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mvideoUri);
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    //When the Activity end it returns a result that is the video
    //check to see if permission was granted to read the photo file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //if saving image call the BitmapView's saveImage method
        //or do the rest of reading the URI of the video
        //  }
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            //toast that says the file of the video
            //   Toast.makeText(this, "Video has been saved to:\n" +
            // intent.getData(), Toast.LENGTH_LONG).show();

            Uri videoUri =  intent.getData();
            videoPath = videoUri.getPath();
            //gets the file name of the video
            String s = videoUri.getLastPathSegment();
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoUri.getPath(),
                    MediaStore.Video.Thumbnails.MINI_KIND);
            ImageView v = (ImageView) findViewById(R.id.video1_thumbnail);
            TextView mtextView = (TextView) findViewById(R.id.video1_tumbnail_title);
            v.setImageBitmap(thumb);
            mtextView.setText("Your video 1");

        }

   }

    //checks for the camera hardware
    private boolean hasCamera(){
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    //check to see if permission was granted to read the photo file
    //Will ask the user if the app can access the video files
   public void getPermissions(){
       if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        //will show explanation of why permission
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set dialog message
        builder.setMessage(R.string.permission_rationale);
        //add ok button to dialog
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialog, int which) {
       //request permission
       requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
       READ_IMAGE_PERMISSION_REQUEST_CODE);
       }
       });
        //display the dialog
        builder.create().show();
        } else {
        //request permission
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
        READ_IMAGE_PERMISSION_REQUEST_CODE);
        }
        } //else {
       // }
   }
}
