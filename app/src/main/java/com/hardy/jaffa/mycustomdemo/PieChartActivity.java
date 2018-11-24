package com.hardy.jaffa.mycustomdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hardy.jaffa.mycustomdemo.model.PieEntity;
import com.hardy.jaffa.mycustomdemo.view.CustomPieChart;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private CustomPieChart pieChart;
    private int [] colors = {0xFFCCFF00,0xFF6495ED,0xFFE32636,0xFF800000,0xFFE6B800,0xFF7CFC00,0xFF6495ED,0xFFE6B800,0xFFE32636};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        pieChart = findViewById(R.id.custom_pie);

        ArrayList<PieEntity> pieEntities = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            pieEntities.add(new PieEntity(i+1,colors[i]));
        }
        pieChart.setDatas(pieEntities);
    }


}
