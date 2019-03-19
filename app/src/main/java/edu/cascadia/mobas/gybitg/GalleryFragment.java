package edu.cascadia.mobas.gybitg;
//This fragment will inflate the layout for this fragment in a
// rootView variable for the recyclerView to utilize
//checks for the camera hardware
//Initializes the RecyclerView sets the Layoutmanager to a LinearLayout
//sets the adapter list to the viewmodel list
//Will intialize the GalleryViewModel the ViewModelProviders class handles the instantiation and
// that is how the application component is passed in in the constructor in the ViewModel class
//Will check to see if permission was granted to read the photo file provided
// there is a permissions code in the Manifest
//Will Displays a toast to ask the user if the app can access the video files
//if the user clicks the ok button, the permission is granted and app can access device video files
//Creates a new intent to open the camera app
//Creates the file to save the video using a Simpledate for each video to be unique
//create the file to save the video
//Will have a onActivityResult called When the Activity ends it returns a result that is the video
//checks to see if permission was granted to read the photo file
//toast that says video saved
//makes a new VideoUpload instance and sets the URI and title
//adds the new Video to the viewmodel's list
//if the cancel button was hit show the message it was canceled

import android.Manifest;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import edu.cascadia.mobas.gybitg.viewmodel.GalleryViewModel;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //RecyclerView Variables
    RecyclerView galleryRecyclerView;
    RecyclerView.Adapter galleryAdapter;
   //request codes
    static final int REQUEST_VIDEO_CAPTURE = 1991;
    private static final int READ_IMAGE_PERMISSION_REQUEST_CODE = 2000;

    private ImageButton mImageButton;
    private GalleryViewModel mGalleryViewModel;

    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment in a rootView variable for the recyclerView to utilize
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryRecyclerView = (RecyclerView) rootView.findViewById(R.id.gallery_recycler_view);
        viewModelInit();
        recyclerInit();
        //call this so the user can give permission to access videos on their
        getPermissions();
        testData();
        FloatingActionButton addVideo = rootView.findViewById(R.id.add_video);
        if(!hasCamera()){
            addVideo.setEnabled(false);
        }

        addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getPermissions();
                dispatchTakeVideoIntent();
            }
        });

        return rootView;
    }

    //Purpose: To intialize the GalleryViewModel
    //ViewModelProviders class handles the instantiation and that is how the
    //application component is passed in in the constructor in the ViewModel class
    //set the observer
    private void viewModelInit() {
        mGalleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        //if Using liveData use instead of mGalleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        /**  mGalleryViewModel.mVideoUpload.observe(this, new Observer<List<VideoUpload>>() {
        @Override
        public void onChanged(@Nullable List<VideoUpload> videoUploads) {

        galleryAdapter = new GalleryAdapter(videoUploads, getActivity());
        //  mImageButton.setImageBitmap();
        }
        });*/

    }


    private void testData(){
        //VideoUpload vid = new VideoUpload("Free Throws", );
        // videolistings.add(vid);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

    //Initializes the RecyclerView
    //sets the Layoutmanager to a LinearLayout
    //Only used because content inside the recyclerView will stay consistent and not change the layout.
    //creates a layout manager and set the recycler view to the linearlayout
    //sets the adapter list to the viewmodel list
    private void recyclerInit(){
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        galleryRecyclerView.hasFixedSize();
        //Data adapter
        galleryAdapter = new GalleryAdapter(mGalleryViewModel.mVideoUpload, getContext());
        //if using LiveData instead of  galleryAdapter = new GalleryAdapter(mGalleryViewModel.mVideoUpload, getContext());
        //galleryAdapter = new GalleryAdapter(mGalleryViewModel.mVideoUpload.getValue(), getContext());
        galleryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //if using liveData do this in the observe in Viewmodelinit
        galleryRecyclerView.setAdapter(galleryAdapter);
    }
    //checks for the camera hardware
    private boolean hasCamera(){
        return (Objects.requireNonNull(getContext()).getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    //Purpose: to check to see if permission was granted to read the photo file
    //Precondition: there is a permissions code in the Manifest
    //Postcondition: Displays a toast to ask the user if the app can access the video files
    //if the user clicks the ok button, the permission is granted and app can access device video files
    public void getPermissions(){
        if (Objects.requireNonNull(getActivity()).checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //will show explanation of why permission
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
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
        }

    }

    //Creates a new intent to open the camera app
    //Creates the file to save the video using a Simpledate for each video to be unique
    //create the file to save the video
    // set the image file name
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(Objects.requireNonNull(getContext()).getPackageManager()) != null) {

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
    //toast that says the file of the video
    //make a new video instance and set the URI and title
    //adds the new Video to the viewmode's list
    //if the cancel button was hit show the message it was canceled
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Uri videoUri =  intent.getData();
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            //gets the file name of the video
            assert videoUri != null;
            String s = videoUri.getLastPathSegment();

            VideoUpload v = new VideoUpload();
            v.setmUri(videoUri);
            v.SetTitle(videoUri.getLastPathSegment());
            mGalleryViewModel.insertVideoUpload(v);
            Toast.makeText(getActivity(), "Video has been saved", Toast.LENGTH_LONG).show();

        }
        //if the cancel button was hit show the message it was canceled
        else {
            Toast.makeText(getActivity(), "Failed to make thumbnail", Toast.LENGTH_LONG).show();

        }

    }

    //Purpose: To turn the video Uri into a string path then converts it into a thumbnail
    //Precondition: There is a non-null Uri
    //Postcondtion: Returns the Bitmap thumbnail of the video
    private  Bitmap convertUriTothumbnail (Uri uri){
        if(uri != null) {
            String path = uri.getPath();
            return ThumbnailUtils.createVideoThumbnail(path,
                    MediaStore.Video.Thumbnails.MINI_KIND);
        }
        else {
            return null;
        }
    }
}
