package com.louisgeek.louisasynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button idbtn = (Button) findViewById(R.id.id_btn);
        final ProgressBar idpb = (ProgressBar) findViewById(R.id.id_pb);
        final TextView idtv = (TextView) findViewById(R.id.id_tv);

        idbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask=new MyAsyncTask(idtv,idpb);
                myAsyncTask.execute("参数");
                myAsyncTask.cancel(true);
            }
        });
    }
}
