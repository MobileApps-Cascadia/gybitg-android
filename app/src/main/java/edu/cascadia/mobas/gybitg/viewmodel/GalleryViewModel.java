package edu.cascadia.mobas.gybitg.viewmodel;

import android.arch.lifecycle.AndroidViewModel;

import java.util.List;

import edu.cascadia.mobas.gybitg.VideoUpload;

//Class will handle logic of data if a repository is not available
//Has a Constructor that will take in an application
//Will insert the VideoUpload passed in
//Will return all the VideoUploads in the list
//Will delete all of the VideoUploads in the list
//Will add all values to the list from a list
//package edu.cascadia.mobas.gybitg.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.cascadia.mobas.gybitg.VideoUpload;

public class GalleryViewModel extends AndroidViewModel {

    public List<VideoUpload> mVideoUpload = new ArrayList<>(); //GallerySampleData.getVideos();
    //for mutableLiveData
    //public MutableLiveData<List<VideoUpload>> mVideoUpload = new MutableLiveData();

    //Purpose: Constructor that will take in an application
    //Precondtion: The application passed in is not null
    //Postcondtion: Will construct an instance of the GalleryViewModel
    public GalleryViewModel(@NonNull Application application) {
        super(application);
        //if using LiveData
        //mVideoUpload.setValue(GallerySampleData.getVideos());
        // mVideoUpload.setValue(new ArrayList<VideoUpload>());
    }

    //Purpose: will insert the VideoUpload passed in
    //Precondition: the videoUpload passed in is not null
    //Postcondition: the VideoUpload will be inserted to the list
    public void insertVideoUpload(VideoUpload videoUpload){
        if(videoUpload != null) {

            mVideoUpload.add(videoUpload);
            //if using LiveData
            // Objects.requireNonNull(mVideoUpload.getValue()).add(videoUpload);
        }
    }

    //Purpose: to return all the VideoUploads in the MutableLiveData list
    //Preconditon: none
    //Postcondition: the list of videos will be returned
    public List<VideoUpload> getAllVideoUploads(){
        //if using LiveData
        //public MutableLiveData<List<VideoUpload>> getAllVideoUploads(){
        return mVideoUpload;
    }

    //Purpose: to delete all of the VideoUploads in the list
    //Precondition: none
    //Postcondition: the list will be emptied
    public void deleteVideoUploads()
    {
        mVideoUpload.clear();
        //if using liveData
        // Objects.requireNonNull(mVideoUpload.getValue()).clear();
    }

    //Purpose: to delete the videoUpload with the title that is passed in
    //Precondtion: the title passed is not null and the list contains the Video
    //Postconditon: the video with the title is deleted
    public void deleteVideoUpload(String title){
        //if using liveData
        /**if(title != null) {
         for (VideoUpload t : Objects.requireNonNull(mVideoUpload.getValue())) {
         if (t.GetTitle().equals(title)) {
         mVideoUpload.getValue().remove(t);
         }
         }
         }*/

        if(title != null) {
            for (VideoUpload t : mVideoUpload) {
                if (t.GetTitle().equals(title)) {
                    mVideoUpload.remove(t);
                }
            }
        }

    }

    //Purpose: To add all values to the list from a list
    //Precondition: The List is not null
    //Postcondition: the list passed in will be added to the list
    //if using LiveData use: public void addAll(List<VideoUpload> videoUpload){
    public void addAll(List<VideoUpload> videoUpload){

        if(videoUpload != null) {

            mVideoUpload.addAll(videoUpload);
            //if using liveData
            // mVideoUpload.getValue().addAll(videoUpload);
        }
    }
}

