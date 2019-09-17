package ua.weather.app;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.*;

import java.util.List;
import java.util.ListIterator;

public class App
{
  public static void main(String[] args) {
    final int CITY_ID = Integer.parseInt(args[0]);
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.ipma.pt/open-data/").addConverterFactory(GsonConverterFactory.create()).build();
    IpmaService service = retrofit.create(IpmaService.class);
    Call<IpmaCityForecast> callSync = service.getForecastForACity( CITY_ID );
    try {
      Response<IpmaCityForecast> apiResponse = callSync.execute();
      IpmaCityForecast forecast = apiResponse.body();
      List<CityForecast> data = forecast.getData();
      ListIterator<CityForecast> it = data.listIterator();

      System.out.println("\nTemperatures for the next following days:\n* * * * * * * * * * * * * * * * * * * * *\n");

      while(it.hasNext()){
        CityForecast tempVal = it.next();
        System.out.println( "Maximum Temperature: " + tempVal.getTMax());
        System.out.println( "Minimum Temperature: " + tempVal.getTMin());
        System.out.println( "");       
      }
      System.exit(1);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
