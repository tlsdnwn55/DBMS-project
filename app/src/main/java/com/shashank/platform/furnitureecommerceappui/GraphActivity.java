package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    PieChart pieChart;

    ArrayList<String> profit =new ArrayList<>();
    ArrayList<String> title =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Toolbar mytoolbar =findViewById(R.id.main2_appbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_24dp);

        Intent intent =getIntent();
        Bundle bundle =new Bundle();
        bundle = intent.getBundleExtra("bundle");
        profit =bundle.getStringArrayList("profit");
        title =bundle.getStringArrayList("title");

        pieChart =findViewById(R.id.chart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,3,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawEntryLabels(false);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        for(int i=0; i<title.size() ; i++){
            yValues.add(new PieEntry(Float.parseFloat(profit.get(i)),title.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(yValues,"재생목록");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        Legend l = pieChart.getLegend();
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        pieChart.setData(data);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(GraphActivity.this,String.valueOf(e.getY())+"원",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}