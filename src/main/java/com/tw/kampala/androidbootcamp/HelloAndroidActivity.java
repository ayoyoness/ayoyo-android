package com.tw.kampala.androidbootcamp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.tw.kampala.androidbootcamp.services.SyncService;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class HelloAndroidActivity extends RoboActivity {

    @InjectView(R.id.sync_button)
    Button syncButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelloAndroidActivity.this, SyncService.class);
                startService(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.tw.kampala.androidbootcamp.R.menu.main, menu);
	return true;
    }

}

