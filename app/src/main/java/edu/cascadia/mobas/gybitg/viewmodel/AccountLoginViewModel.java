package edu.cascadia.mobas.gybitg.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.cascadia.mobas.gybitg.ProfileActivity;
import edu.cascadia.mobas.gybitg.R;
import edu.cascadia.mobas.gybitg.db.AppRepository;

public class AccountLoginViewModel extends ViewModel {
    private AppRepository appRepo;

    Button login_btn = (Button) findViewById(R.id.email_sign_in_button);
    final EditText email = (EditText) findViewById(R.id.email);
    final EditText password = (EditText) findViewById(R.id.password);
    final Intent profile_page = new Intent(this, ProfileActivity.class);

        login_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(email.getText().toString().equals("ksmith@gmail.com") && password.getText().toString().equals("Baskets97")){
                startActivity(profile_page);
            }
        }
    });

}
