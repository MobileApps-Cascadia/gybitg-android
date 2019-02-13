package edu.cascadia.mobas.gybitg;

public class VideoUpload {
    private String title;
    private int imageURL;

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

}
