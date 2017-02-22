package com.example.manasaa.remindertask;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by manasa.a on 20-02-2017.
 */

public class InputDailog  extends Activity{
    private static final String TAG=" InputDailogclass";
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d(TAG,"called onCreateDailog");
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.input_dailog);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        EditText taskName = (EditText) dialog.findViewById(R.id.taskName);
        EditText taskTime = (EditText) dialog.findViewById(R.id.taskTime);

        dialog.show();
    }
}
