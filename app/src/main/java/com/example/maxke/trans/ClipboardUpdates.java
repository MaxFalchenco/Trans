package com.example.maxke.trans;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by maxke on 22.05.2017.
 */

public class ClipboardUpdates {

    public static String MyClipboart()
    {

        final String StrforClip = "";

        BroadcastReceiver ClipboardListener = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {

                //TextView textView3 = (TextView) findViewById(R.id.textView3);

                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null)
                    clipboard.setText(StrforClip);

            }
        };


        return StrforClip;
    }

}
