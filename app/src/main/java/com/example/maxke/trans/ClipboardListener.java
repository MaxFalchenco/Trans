package com.example.maxke.trans;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ClipboardListener extends BroadcastReceiver {



    String str = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        System.out.println("pinkvdabfjfa");

        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null)
        {
            System.out.println("hi");
            //button.setText(clipData.getItemAt(0).getText());
        }
    }


    }

