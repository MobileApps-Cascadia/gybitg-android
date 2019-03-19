package edu.cascadia.mobas.gybitg;

import android.net.Uri;

public class VideoUpload {
    private String title;
    private int imageURL;
    private Uri mUri;
    public VideoUpload (){

    }
    public VideoUpload(String title, int imageURL){
        this.title = title;
        this.imageURL = imageURL;
    }

    public void SetTitle(String title){
        this.title = title;
    }

    public void SetImage(int imageURL){
        this.imageURL = imageURL;
    }

    public String GetTitle(){
        return title;
    }

    public int GetImageURL(){
        return imageURL;
    }

    public void setmUri(Uri uri){
        this.mUri = uri;
    }

    public Uri getmUri(){
        return mUri;
    }

}
