package com.adphi.apps.firebasenotifications_test_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    // The Keys
    private String TITLE = "Title";
    private String CONTENT = "Content";
    private String SUBTEXT = "SubText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Service
        Intent serviceIntent = new Intent(this, NotificationService.class);
        startService(serviceIntent);

        // Get the Database
        FirebaseDatabase database = FirebaseHelper.getDatabase();
        // Get the Notification Reference
        final DatabaseReference notificationRef = database.getReference("notification");
        // Keep the Database sync in case of loosing connexion
        notificationRef.keepSynced(true);

        // Send Notification on Send Click
        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Edit Text
                EditText editTextTitle = findViewById(R.id.editTextTitle);
                EditText editTextContent = findViewById(R.id.editTextContent);
                EditText editTextSubText = findViewById(R.id.editTextSubText);

                // Get Text
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                String subtext = editTextSubText.getText().toString();
                // Store in a map
                HashMap<String, String> notification = new HashMap<String, String>();
                notification.put(TITLE, title);
                notification.put(CONTENT, content);
                notification.put(SUBTEXT, subtext);
                // Send the map
                notificationRef.setValue(notification);
            }
        });
    }
}
