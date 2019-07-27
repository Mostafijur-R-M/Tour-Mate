package com.example.tourmate.mostafijur.tourmate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tourmate.mostafijur.tourmate.weather.CurrentWeatherResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {


    private static final String TAG = CurrentFragment.class.getSimpleName();
    private TextView cityTV, countryTV, tempTV, descriptionTV;
    private String unit = "metric";
    private ImageView iconIV;
    public CurrentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        cityTV = view.findViewById(R.id.cityTVID);
        countryTV = view.findViewById(R.id.countryTVID);
        tempTV = view.findViewById(R.id.tempTVID);
        descriptionTV = view.findViewById(R.id.descriptionTVID);
        iconIV = view.findViewById(R.id.iconIV);
        return view;
    }

    public void setLatLng(double lat, double lng){
        //tempTV.setText(String.valueOf(lat)+", "+String.valueOf(lng));

        getCurrentWeatherData(lat, lng);

    }

    private void getCurrentWeatherData(double lat, double lng) {

        String api = getResources().getString(R.string.weather_api);
        String endUrl = String.format("weather?lat=%f&lon=%f&units=%s&appid=%s",lat, lng, unit, api);
        RetrofitClient.getService().getCurrentWeatherResponse(endUrl)
                .enqueue(new Callback<CurrentWeatherResponse>() {
                    @Override
                    public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                        if(response.isSuccessful()){
                            CurrentWeatherResponse currentWeatherResponse =
                                    response.body();
                            double temp = currentWeatherResponse.getMain().getTemp();
                            String city = currentWeatherResponse.getName();
                            String country = currentWeatherResponse.getSys().getCountry();
                            String description = currentWeatherResponse.getWeather().get(0)
                                    .getDescription();
                            String icon = currentWeatherResponse.getWeather().get(0)
                                    .getIcon();

                            cityTV.setText(city);
                            countryTV.setText(country);
                            Picasso.get()
                                    .load(RetrofitClient.ICON_URL+icon+".png")
                                    .into(iconIV);
                            tempTV.setText(String .valueOf(temp)+"°C");
                            descriptionTV.setText(description);
                            /*tempTV.setText(String.valueOf(temp)+"\n"+
                            description+"\n"+
                            city+"\n"+
                            country);*/
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }

}
