package com.example.completesigininfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class completeDeatailCovid extends AppCompatActivity {

    public TextView cases1,deaths1,recovered1,active1,country1,continent1,flag1,Critical1,casesperonemillion1,deathspermillion1;
    public TextView tests1,testsperonemillion1,population1,onecaseperpeople1,onetestperpeople1,onedeathperpeople1;
    public TextView recoveredpermillion1,activeperonemillion1,criticalperonemillion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_deatail_covid);

        cases1=findViewById(R.id.textView31);
        deaths1=findViewById(R.id.textView30);
        recovered1=findViewById(R.id.textView29);
        active1=findViewById(R.id.textView28);
        country1=findViewById(R.id.textView27);
        continent1=findViewById(R.id.textView26);
        Critical1=findViewById(R.id.textView23);
        casesperonemillion1=findViewById(R.id.textView22);
        deathspermillion1=findViewById(R.id.textView21);
        tests1=findViewById(R.id.textView20);
        testsperonemillion1=findViewById(R.id.textView19);
        population1=findViewById(R.id.textView18);
        onecaseperpeople1=findViewById(R.id.textView17);
        onetestperpeople1=findViewById(R.id.textView16);

        recoveredpermillion1=findViewById(R.id.textView7);
        activeperonemillion1=findViewById(R.id.textView4);
        criticalperonemillion1=findViewById(R.id.textView25);



        String cases,deaths,recovered,active,country,continent,Critical,casesperonemillion,deathspermillion,tests;
        String testsperonemillion,population,onecaseperpeople,onetestperpeople,onedeathperpeople;
        String recoveredpermillion,activeperonemillion,criticalperonemillion;

        Intent intent=getIntent();
        Bundle bundle=getIntent().getExtras();

        country=bundle.getString("country");
        continent=bundle.getString("continent");
        cases=bundle.getString("cases");
        recovered=bundle.getString("recovered");
        active=bundle.getString("active");
        deaths=bundle.getString("death");

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

        country1.setText("Country- "+country);
        continent1.setText("Continent- "+continent);
        cases1.setText("cases- "+cases);
        recovered1.setText("recovered- "+recovered);
        active1.setText("active- "+active);
        deaths1.setText("deaths- "+deaths);
        Critical1.setText("Critical- "+Critical);
        casesperonemillion1.setText("Case per One Million- "+casesperonemillion);
        deathspermillion1.setText("deaths per one Million-"+deathspermillion);
        tests1.setText("Covid Tests- "+tests);
        testsperonemillion1.setText("Tests per one million- "+testsperonemillion);
        population1.setText("Population- "+population);
        onecaseperpeople1.setText("One case per people- "+onecaseperpeople);
        onetestperpeople1.setText("One test per people- "+onetestperpeople);

        recoveredpermillion1.setText("recovered per million- "+recoveredpermillion);
        activeperonemillion1.setText("active per million- "+activeperonemillion);
        criticalperonemillion1.setText("critical per million- "+criticalperonemillion);




    }
}