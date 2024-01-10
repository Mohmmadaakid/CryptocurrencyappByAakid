package com.example.cryptocurrencytask;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoinMarketCapService {

    @GET("v1/cryptocurrency/listings/latest")
    Call<CoinMarketCapResponse> getData(@Header("X-CMC_PRO_API_KEY") String apiKey);


    @GET("v2/cryptocurrency/info")
    Call<CoinMetadataResponse> getMetadata(@Header("X-CMC_PRO_API_KEY") String apiKey, @Query("symbol") String symbol);
}
//    @GET("v1/cryptocurrency/info/{symbol}")
//    Call<CoinMetadataResponse> getMeta(
//            @Header("X-CMC_PRO_API_KEY") String apiKey,
//            @Path("symbol") String symbol);