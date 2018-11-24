package com.hardy.jaffa.mycustomdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bing).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
          switch(view.getId()){
              case R.id.bing:
                  intent = new Intent(this,PieChartActivity.class);
                  startActivity(intent);
                  break;
          }
    }
}
