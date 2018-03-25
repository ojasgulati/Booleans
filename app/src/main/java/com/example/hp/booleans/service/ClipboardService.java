package com.example.hp.booleans.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static com.example.hp.booleans.database.DatabaseUtil.getAccuracy;


/**
 * Created by HP on 25-03-2018.
 */

public class ClipboardService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipBoard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

            @Override
            public void onPrimaryClipChanged() {
                Log.i("String ","clipboard");
                ClipData clipData = clipBoard.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                String text = item.getText().toString();
                int accuracy = getAccuracy(getApplicationContext(), text);
                if (accuracy > 0){
                    Toast.makeText(getApplicationContext(),"Fake News most likely "+ Integer.valueOf(accuracy)+"%",Toast.LENGTH_LONG).show();
                    Log.i("String ","Fake News most likely "+ Integer.valueOf(accuracy)+"%");
                }
                Log.i("String ","real");

                // Access your context here using YourActivityName.this
            }
        });
        return START_STICKY;
    }
}
