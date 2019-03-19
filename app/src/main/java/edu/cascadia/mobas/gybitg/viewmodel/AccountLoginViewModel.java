package edu.cascadia.mobas.gybitg.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.cascadia.mobas.gybitg.MainActivity;
import edu.cascadia.mobas.gybitg.ProfileActivity;
import edu.cascadia.mobas.gybitg.R;
import edu.cascadia.mobas.gybitg.db.AppRepository;

public class AccountLoginViewModel extends ViewModel {
    private AppRepository appRepo;

    private MutableLiveData<String> email;
    private MutableLiveData<String> password;

    public AccountLoginViewModel(){
        this.email = new MutableLiveData<>();
        this.password = new MutableLiveData<>();
    }

    public void setEmail(String val){
        email.setValue(val);
    }
    public void setPassword(String val){
        password.setValue(val);
    }

    public String getEmail(){
        return email.getValue();
    }

    public String getPassword(){
        return password.getValue();
    }

    public Boolean correctLogin(){
        if(email.getValue().equals("ksmith@gmail.com") && password.getValue().equals("Baskets97")){
            return true;
        }
        return false;
    }

    public void onLoginButtonClicked(CharSequence email, CharSequence password, Intent intent){
        setEmail(email.toString());
        setPassword(password.toString());
        correctLogin();
    }
}


