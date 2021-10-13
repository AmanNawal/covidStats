package com.example.completesigininfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class piechartactivity extends AppCompatActivity {
private PieChart pieChart;
private TextView coun,con,act,dead,rec,tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartactivity);
        coun=findViewById(R.id.textView8);
        con=findViewById(R.id.textView9);
        pieChart=findViewById(R.id.chart1);
        act=findViewById(R.id.textView11);
        tot=findViewById(R.id.textView10);
        dead=findViewById(R.id.textView12);
        rec=findViewById(R.id.textView13);
        Float active,death,recovered,totalcases;
        String country,continent,flag;
        Intent intent=getIntent();
        Bundle bundle=getIntent().getExtras();
        active=Float.parseFloat(bundle.getString("active"));
        death=Float.parseFloat(bundle.getString("death"));
        recovered=Float.parseFloat(bundle.getString("recovered"));
        totalcases=Float.parseFloat(bundle.getString("totalcases"));
        country=bundle.getString("country");
        continent=bundle.getString("continent");
        flag=bundle.getString("flag");




        Float activeper=(active/totalcases)*100;
        Float recoveredper=(recovered/totalcases)*100;
        Float deathper=(death/totalcases)*100;

        activeper=activeper/100;
        recoveredper=recoveredper/100;
        deathper=deathper/100;

        con.setText("Continent- "+continent);
        coun.setText("Country- "+country);
        act.setText("Active Cases- "+Integer.parseInt(bundle.getString("active")));
        tot.setText("Total Cases- "+Integer.parseInt(bundle.getString("totalcases")));
        dead.setText("Deaths- "+Integer.parseInt(bundle.getString("death")));
        rec.setText("Recovered- "+Integer.parseInt(bundle.getString("recovered")));

        ArrayList<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(activeper,"Active"));
        entries.add(new PieEntry(recoveredper,"Recovered"));
        entries.add(new PieEntry(deathper,"deaths"));

        ArrayList<Integer>colors=new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS)
        {
            colors.add(color);
        }
        PieDataSet dataSet=new PieDataSet(entries,"");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(0);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("PIE CHART");
        pieChart.setCenterTextSize(19);
        pieChart.getDescription().setEnabled(false);
        Legend l=pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);

        pieChart.animateY(1000, Easing.EaseInOutQuad);

    }


}