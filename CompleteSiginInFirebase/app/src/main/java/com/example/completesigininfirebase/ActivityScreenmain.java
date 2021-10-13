package com.example.completesigininfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityScreenmain extends AppCompatActivity {
public ArrayList<CovidData>Arraylist;
public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenmain);
        recyclerView=findViewById(R.id.recyclerview);
       Arraylist=new ArrayList<CovidData>();


        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);


        String url = "https://corona.lmao.ninja/v2/countries";
        RequestQueue queue= Volley.newRequestQueue(this);

        
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int reslength= response.length();
               // Toast.makeText(getApplicationContext(), reslength+" ", Toast.LENGTH_SHORT).show();
               for(int i=0;i<reslength;i++)
               {
                   try {
                       CovidData covidData=new CovidData();
                       JSONObject jsonObject=response.getJSONObject(i);
                        covidData.setCountry(jsonObject.getString("country"));
                        covidData.setCritical(jsonObject.getString("critical"));
                        covidData.setCasespermillion(jsonObject.getString("casesPerOneMillion"));
                        covidData.setDeathspermillion(jsonObject.getString("deathsPerOneMillion"));
                        covidData.setTests(jsonObject.getString("tests"));
                        covidData.setTestspermillion(jsonObject.getString("testsPerOneMillion"));
                        covidData.setPopulation(jsonObject.getString("population"));
                        covidData.setOnecaseperpeople(jsonObject.getString("oneCasePerPeople"));
                        covidData.setOnedeathpewpeople(jsonObject.getString("oneDeathPerPeople"));
                        covidData.setOnetestperpeople(jsonObject.getString("oneTestPerPeople"));
                        covidData.setActicepermillion(jsonObject.getString("activePerOneMillion"));
                        covidData.setRecoveredperonemillion(jsonObject.getString("recoveredPerOneMillion"));
                        covidData.setCritcalperonemillion(jsonObject.getString("criticalPerOneMillion"));
                        covidData.setContinent(jsonObject.getString("continent"));
                        JSONObject object2=jsonObject.getJSONObject("countryInfo");
                        covidData.setFlagImage(object2.getString("flag"));
                        covidData.setCases(jsonObject.getString("cases"));
                        covidData.setActive(jsonObject.getString("active"));
                        covidData.setDeath(jsonObject.getString("deaths"));
                        covidData.setRecovered(jsonObject.getString("recovered"));
                        covidData.setTodayCases(jsonObject.getString("todayCases"));
                        covidData.setTodayDeath(jsonObject.getString("todayDeaths"));
                        covidData.setTodayRecovered(jsonObject.getString("todayRecovered"));

                     //  Toast.makeText(getApplicationContext(), jsonObject.getString("country"), Toast.LENGTH_SHORT).show();
                      // Toast.makeText(getApplicationContext(), covidData.getCountry()+" "+covidData.getContinent(), Toast.LENGTH_SHORT).show();
                       Arraylist.add(covidData);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }

                CoronaAdapterview adapter=new CoronaAdapterview(Arraylist,ActivityScreenmain.this,progressBar);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityScreenmain.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                    if(Arraylist.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                    }
                    else if(!Arraylist.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "not empty", Toast.LENGTH_SHORT).show();
                    }



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to read JSON", Toast.LENGTH_SHORT).show();
            }
        });





        queue.add(jsonArrayRequest);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {            //create an option menu
        MenuInflater Inflater=getMenuInflater();          //converts our layout to view Object
        Inflater.inflate(R.menu.toolbarman,menu);
        return true;
    }

       @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {   //this method is used to provide functionality when an item is pressed



      switch(item.getItemId())
      {
          case R.id.profile:
              Intent intent=new Intent(ActivityScreenmain.this,profile.class);
              startActivity(intent);
              return true;

          case R.id.signout:
              FirebaseAuth.getInstance().signOut();
              Intent mainIntent = new Intent(ActivityScreenmain.this, MainActivity.class);
              startActivity(mainIntent);
              Toast.makeText(this, "successfully signed out", Toast.LENGTH_SHORT).show();
              finish();
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }


    }


}