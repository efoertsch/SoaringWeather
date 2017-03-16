package com.fisincorporated.aviationweather.test;

import com.fisincorporated.aviationweather.data.metars.MetarResponse;
import com.fisincorporated.aviationweather.retrofit.AirportMetarService;
import com.fisincorporated.aviationweather.retrofit.AppRetrofit;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;



public class TestMockResponse {

    String airportList = "KORH KBOS";

    @Test
    public void testMockRetrofitResponse() throws IOException {

        // This MockInterceptor always returns same canned Metar and Taf info
        Retrofit retrofit = new AppRetrofit(new retrofit.MockInterceptor()).getRetrofit();

        AirportMetarService service = retrofit.create(AirportMetarService.class);
        Call<MetarResponse> call = service.mostRecentMetarForEachAirport(airportList, 2);
        MetarResponse tafResponse = call.execute().body();
        // Can loop through reponse to check data
    }

}