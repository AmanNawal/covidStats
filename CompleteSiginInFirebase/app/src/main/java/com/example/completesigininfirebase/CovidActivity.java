package com.example.completesigininfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class CovidActivity extends AppCompatActivity {

    ImageView active,deaths,recovered,totalcases,Countryimage;
    TextView Countryname,Continentname,activecases,Totaldeaths,totalRecovered,totalCasesText,graphicalrep;
    public CardView activecard,totalcard,recoveredcard,deathcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);


        active=findViewById(R.id.imageView3);
        deaths=findViewById(R.id.imageView6);
        recovered=findViewById(R.id.imageView5);
        totalcases=findViewById(R.id.imageView4);
        Countryimage=findViewById(R.id.imageView2);
        Countryname=findViewById(R.id.countryname);
        Continentname=findViewById(R.id.continentname);
        activecases=findViewById(R.id.activecases);
        Totaldeaths=findViewById(R.id.deaths);
        totalRecovered=findViewById(R.id.recovered);
        totalCasesText=findViewById(R.id.cases);
        graphicalrep=findViewById(R.id.graphicalRepresentation);
        activecard=findViewById(R.id.cardView2);
        recoveredcard=findViewById(R.id.cardView5);
        deathcard=findViewById(R.id.cardView6);
        totalcard=findViewById(R.id.cardView3);
        Intent intent=getIntent();
        Bundle bundle=getIntent().getExtras();
       // CovidData covidData=(CovidData) getIntent().getSerializableExtra("covidclass");
        String cases,deaths,recovered,active,country,continent,flag,Critical,casesperonemillion,deathspermillion,tests;
        String testsperonemillion,population,onecaseperpeople,onetestperpeople,onedeathperpeople;
        String recoveredpermillion,activeperonemillion,criticalperonemillion;

        flag=bundle.getString("imageUrl");
        country=bundle.getString("country");
        continent=bundle.getString("continent");
        cases=bundle.getString("totalCases");
        recovered=bundle.getString("recovered");
        active=bundle.getString("activecases");
        deaths=bundle.getString("deaths");

        Critical=bundle.getString("Critical");
        casesperonemillion= bundle.getString("casesperonemillion");
        deathspermillion= bundle.getString("deathspermillion");;
        tests= bundle.getString("tests");
        testsperonemillion= bundle.getString("testsperonemillion");
        population= bundle.getString("population");
        onecaseperpeople= bundle.getString("onecaseperpeople");
        onetestperpeople= bundle.getString("onetestperpeople");

        recoveredpermillion= bundle.getString("recoveredpermillion");;
        activeperonemillion= bundle.getString("activeperonemillion");
        criticalperonemillion= bundle.getString("criticalperonemillion");





        Countryname.setText("Country- "+country);
        Continentname.setText("Continent- "+continent);
        activecases.setText("Active cases- "+active);
        Totaldeaths.setText("Deaths- "+deaths);
        totalRecovered.setText("recovered- "+recovered);
        totalCasesText.setText("Cases- "+cases);
        progressBar.setVisibility(View.INVISIBLE);
        Glide.with(this).load(flag).into(Countryimage);
        Intent intentdetail=new Intent(CovidActivity.this,completeDeatailCovid.class);
        Bundle bundledetail=new Bundle();

        bundledetail.putString("Critical",Critical);
        bundledetail.putString("casesperonemillion",casesperonemillion);
        bundledetail.putString("deathspermillion",deathspermillion);
        bundledetail.putString("tests",tests);
        bundledetail.putString("testsperonemillion",testsperonemillion);
        bundledetail.putString("population",population);
        bundledetail.putString("onecaseperpeople",onecaseperpeople);
        bundledetail.putString("onetestperpeople",onetestperpeople);

        bundledetail.putString("recoveredpermillion",recoveredpermillion);
        bundledetail.putString("activeperonemillion",activeperonemillion);
        bundledetail.putString("recoveredperonemillion",recoveredpermillion);
        bundledetail.putString("criticalperonemillion",criticalperonemillion);
        bundledetail.putString("active",active);
        bundledetail.putString("death",deaths);
        bundledetail.putString("recovered",recovered);
        bundledetail.putString("totalcases",cases);
        bundledetail.putString("country",country);
        bundledetail.putString("continent",continent);
        bundledetail.putString("cases",cases);

        graphicalrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CovidActivity.this,piechartactivity.class);
                Bundle bundlepie=new Bundle();
                bundlepie.putString("active",active);
                bundlepie.putString("death",deaths);
                bundlepie.putString("recovered",recovered);
                bundlepie.putString("totalcases",cases);
                bundlepie.putString("country",country);
                bundlepie.putString("continent",continent);
                bundlepie.putString("flag",flag);
                intent.putExtras(bundlepie);
                startActivity(intent);


            }
        });
activecard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    intentdetail.putExtras(bundledetail);
    startActivity(intentdetail);
    }
});
recoveredcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        intentdetail.putExtras(bundledetail);
        startActivity(intentdetail);
    }
});
deathcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        intentdetail.putExtras(bundledetail);
        startActivity(intentdetail);
    }
});

totalcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        intentdetail.putExtras(bundledetail);
        startActivity(intentdetail);
    }
});



    }
}