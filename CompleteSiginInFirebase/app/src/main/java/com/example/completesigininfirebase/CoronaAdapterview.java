package com.example.completesigininfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoronaAdapterview extends RecyclerView.Adapter<UserViewHolder> {

public ArrayList<CovidData> covidData;
Context mainCon;
ProgressBar progressBar;
public CoronaAdapterview(ArrayList<CovidData> data, Context con, ProgressBar progress)
{
    this.covidData=data;
    this.mainCon=con;
    this.progressBar=progress;
}

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_corona_adapterview,parent,false);
        UserViewHolder viewHolder=new UserViewHolder(view);   //passing our view to the viewHolder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    String coun="Country- "+covidData.get(position).getCountry();
    holder.country.setText(coun);
    String conti="Continent- "+covidData.get(position).getContinent();
    holder.continent.setText(conti);
        Glide.with(mainCon).load(covidData.get(position).getFlagImage()).into(holder.countryFlag);
        progressBar.setVisibility(View.INVISIBLE);
    /*holder.country.setText(covidData.get(position).getCountry());
    holder.continent.setText(covidData.get(position).getContinent());*/
    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent covidIntent=new Intent(mainCon,CovidActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("country",covidData.get(position).getCountry());
            bundle.putString("continent",covidData.get(position).getContinent());
            bundle.putString("imageUrl",covidData.get(position).getFlagImage());
            bundle.putString("activecases",covidData.get(position).getActive());
            bundle.putString("totalCases",covidData.get(position).getCases());
            bundle.putString("recovered",covidData.get(position).getRecovered());
            bundle.putString("deaths",covidData.get(position).getDeath());
            bundle.putString("Critical",covidData.get(position).getCritical());
            bundle.putString("casesperonemillion",covidData.get(position).getCasespermillion());
            bundle.putString("deathspermillion",covidData.get(position).getDeathspermillion());
            bundle.putString("tests",covidData.get(position).getTests());
            bundle.putString("testsperonemillion",covidData.get(position).getTestspermillion());
            bundle.putString("population",covidData.get(position).getPopulation());
            bundle.putString("onecaseperpeople",covidData.get(position).getOnecaseperpeople());
            bundle.putString("onetestperpeople",covidData.get(position).getOnetestperpeople());

            bundle.putString("recoveredpermillion",covidData.get(position).getRecoveredperonemillion());
            bundle.putString("activeperonemillion",covidData.get(position).getActicepermillion());
            bundle.putString("recoveredperonemillion",covidData.get(position).getRecoveredperonemillion());
            bundle.putString("criticalperonemillion",covidData.get(position).getCritcalperonemillion());
          //  covidIntent.putExtra("covidclass",covidData);

            covidIntent.putExtras(bundle);
            mainCon.startActivity(covidIntent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return covidData.size();
    }


}
class UserViewHolder extends RecyclerView.ViewHolder
{
    public TextView country,continent;
    public ImageView countryFlag;
    public ConstraintLayout constraintLayout;
    public LinearLayout layout;
    public CardView cardView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        country=itemView.findViewById(R.id.Country);
        continent=itemView.findViewById(R.id.Continent);
        countryFlag=itemView.findViewById(R.id.countryFlag);
        constraintLayout=itemView.findViewById(R.id.ConstraintLayout);

        cardView=itemView.findViewById(R.id.cardView);


    }
}