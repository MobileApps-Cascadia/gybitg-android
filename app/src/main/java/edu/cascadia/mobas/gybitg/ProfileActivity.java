package edu.cascadia.mobas.gybitg;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    //Fragment Transaction Manager
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //References to the tab headers
        TextView stats_tab = findViewById(R.id.stats_label);
        TextView gallery_tab = findViewById(R.id.gallery_label);
        TextView feed_tab = findViewById(R.id.feed_label);
        TextView contact_tab = findViewById(R.id.contact_label);

        if(findViewById(R.id.profile_fragment_container) != null){

            if(savedInstanceState != null){
                return;
            }

            //Stats Fragment
            final StatFragment stats = new StatFragment();
            // Stats History Fragment
            final StatHistoryFragment statsHistory = new StatHistoryFragment();

            //Gallery Fragment
            final GalleryFragment gallery = new GalleryFragment();
            //Contact Fragment
            final ContactFragment contact= new ContactFragment();
            //Feed Fragment
            final FeedFragment feed = new FeedFragment();





            //Start the app off with the profile
            getSupportFragmentManager().beginTransaction().add(R.id.profile_fragment_container, stats).commit();


            gallery_tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_fragment_container, gallery);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            stats_tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_fragment_container, stats);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });


            feed_tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_fragment_container, feed);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            contact_tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.profile_fragment_container, contact);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });





        }
    }
}
