package edu.cascadia.mobas.gybitg.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import edu.cascadia.mobas.gybitg.db.AppRepository;

public class AccountLoginViewModel extends ViewModel {

    private String email;
    private String password;

    public AccountLoginViewModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String val){

        email = val;
    }
    public void setPassword(String val){

        password = val;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public Boolean correctLogin(){
        if(email.equals("ksmith@gmail.com") && password.equals("Baskets97")){
            return true;
        }
        return false;
    }
}


