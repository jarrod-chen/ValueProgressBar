package com.jarrod.diyview;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jarrod.diyview.view.ValueProgressBar;

public class MainActivity extends AppCompatActivity {

    private ValueProgressBar progressBar;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ValueProgressBar) findViewById(R.id.progressbar);

        start = (Button) findViewById(R.id.button);
        start.setOnClickListener(new StartClick());
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setProgress(msg.what);
            handler.postDelayed(updateProgress, 1000);
        }
    };

    class StartClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            progressBar.setVisibility(View.VISIBLE);
//            handler.post(updateProgress);
            handler.postDelayed(updateProgress, 1000);
        }
    }

    Runnable updateProgress = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            i += 10;
//            Message msg = handler.obtainMessage();
//            msg.arg1 = i;
//            handler.sendMessage(msg);
            if (i <= 100) {
                handler.sendEmptyMessage(i);
            }
            if (i == 100) {
                handler.removeCallbacks(updateProgress);
            }
        }
    };

}
