package com.example.cryptocurrencytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static final String API_KEY = "d4296abe-bfee-4ca4-bc5c-78be225506db";
    private RecyclerView recyclerView;
    private CryptoAdapter cryptoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        CoinMarketCapService service = ApiClient.getClient().create(CoinMarketCapService.class);
        Call<CoinMarketCapResponse> call = service.getData(API_KEY);

        call.enqueue(new Callback<CoinMarketCapResponse>() {
            @Override
            public void onResponse(Call<CoinMarketCapResponse> call, Response<CoinMarketCapResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CoinMarketCapResponse.CryptoData> cryptoList = response.body().getData();

                    Collections.sort(cryptoList, new Comparator<CoinMarketCapResponse.CryptoData>() {
                        @Override
                        public int compare(CoinMarketCapResponse.CryptoData crypto1, CoinMarketCapResponse.CryptoData crypto2) {
                            return Integer.compare(crypto1.getCmcRank(),crypto2.getCmcRank());
                        }
                    });

                    if(cryptoList.size()>=20)
                    {

                        List<CoinMarketCapResponse.CryptoData> top20=cryptoList.subList(0,20);


                        cryptoAdapter = new CryptoAdapter(top20);
                        recyclerView.setAdapter(cryptoAdapter);

                    }





//                    cryptoAdapter = new CryptoAdapter(cryptoList);
//                    recyclerView.setAdapter(cryptoAdapter);


                } else {
                    Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CoinMarketCapResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
