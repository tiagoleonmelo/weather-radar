package ua.weather.app;

// Libraries for the REST API
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.*;

// Logging Libraries
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// Java Libraries
import java.util.List;
import java.util.ListIterator;


public class App
{
  private static Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {
    int CITY_ID = -1;
    final String CITY_NAME = args[0];

    //

    Retrofit retrofit_ = new Retrofit.Builder().baseUrl("http://api.ipma.pt/open-data/").addConverterFactory(GsonConverterFactory.create()).build();
    IpmaService service_ = retrofit_.create(IpmaService.class);
    Call<IpmaCityCodes> callSync_ = service_.getForecastForName();

    try {
      Response<IpmaCityCodes> apiResponse_ = callSync_.execute();
      IpmaCityCodes forecast_ = apiResponse_.body();
      List<CityCodes> data_ = forecast_.getData();
      ListIterator<CityCodes> it_ = data_.listIterator();
      CityCodes temp;

      while(it_.hasNext()){
        temp = it_.next();
        if(temp.getLocal().toUpperCase().equals(CITY_NAME.toUpperCase()) ){
          CITY_ID = temp.getGlobalIdLocal();
        }
      }

      if(CITY_ID == -1){
        logger.error("ERROR: City not found");
        System.exit(1);
      }

    } catch (Exception e) {
      e.printStackTrace();
      
    }



    //
    
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.ipma.pt/open-data/").addConverterFactory(GsonConverterFactory.create()).build();
    IpmaService service = retrofit.create(IpmaService.class); 
    Call<IpmaCityForecast> callSync = service.getForecastForACity( CITY_ID );
    try {
      Response<IpmaCityForecast> apiResponse = callSync.execute();
      logger.info("Fetched weather data successfully");

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
      logger.error("ERROR: Check terminal for more details");
    }
  }
}
