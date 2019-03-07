//This Class will show a list of the videos the user has in thumbnails
//Will have a add Video button that will open the camera app
//Camera app will take a video and play the video
//The video will be converted to a thumbnail and added to list
package edu.cascadia.mobas.gybitg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1991;
    private static final int READ_IMAGE_PERMISSION_REQUEST_CODE = 2000;
    private ImageView mImageView;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery2);
    }
}
