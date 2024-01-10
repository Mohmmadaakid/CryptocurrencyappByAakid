package com.example.cryptocurrencytask;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> {

    private List<CoinMarketCapResponse.CryptoData> cryptoList;

    public CryptoAdapter(List<CoinMarketCapResponse.CryptoData> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crypto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoinMarketCapResponse.CryptoData crypto = cryptoList.get(position);
        holder.nameTextView.setText(crypto.getName());
        holder.symbolTextView.setText(crypto.getSymbol());

        double priceInUSD = crypto.getQuote().getUsd().getPrice();
        holder.usdprice.setText("$" + priceInUSD);

        double change24h = crypto.getQuote().getUsd().getPercentChange24h();
        if (change24h > 0) {
            holder.percentage.setText("+" + change24h + "%");
            holder.percentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_green_dark));
            holder.graph.setImageResource(R.drawable.ic_baseline_arrow_outward_24);
        } else if (change24h < 0) {
            holder.percentage.setText(change24h + "%");
            holder.percentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_red_dark));
            holder.graph.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
        } else {
            holder.percentage.setText(change24h + "%");
            holder.percentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.graph.setVisibility(View.INVISIBLE);
        }

        // Fetch logo using Picasso
        fetchLogoAndLoad(crypto.getSymbol(), holder.logo);
    }

    private void fetchLogoAndLoad(String symbol, ImageView logo) {
        CoinMarketCapService service = ApiClient.getClient().create(CoinMarketCapService.class);
        Call<CoinMetadataResponse> call = service.getMetadata(MainActivity.API_KEY, symbol);

        call.enqueue(new Callback<CoinMetadataResponse>() {
            @Override
            public void onResponse(Call<CoinMetadataResponse> call, Response<CoinMetadataResponse> response) {


                if (response.isSuccessful() && response.body() != null) {
                    CoinMetadataResponse.MetadataData metadataData = response.body().getData();

                    if (metadataData != null) {
                        CoinMetadataResponse.CryptoCurrency cryptoCurrency = metadataData.getCryptoCurrency();

                        if (cryptoCurrency != null) {
                            String logoUrl = cryptoCurrency.getLogo();
                            Log.d("CryptoAdapter", "Logo URL: " + logoUrl);

                            if (logoUrl != null && !logoUrl.isEmpty()) {
                                // Load logo using Picasso
                                Picasso.get().load(logoUrl).into(logo);
                                Log.d("CryptoAdapter", "Logo loaded successfully");
                            } else {
                                // Handle empty or null logo URL
                                Log.e("CryptoAdapter", "Empty or null logo URL");
                            }
                        } else {
                            // Handle null cryptoCurrency
                            Log.e("CryptoAdapter", "Null cryptoCurrency for symbol: " + symbol);

                            // Log the entire response body for investigation
                            Log.e("CryptoAdapter", "Response Body: " + response.body().toString());
                        }
                    } else {
                        // Handle null metadataData
                        Log.e("CryptoAdapter", "Null metadataData for symbol: " + symbol);
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("CryptoAdapter", "Unsuccessful API response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CoinMetadataResponse> call, Throwable t) {
                // Handle failure
                Log.e("CryptoAdapter", "Failure for symbol: " + symbol + ", Error: " + t.getMessage());
                t.printStackTrace(); // Log the stack trace for more details
            }
        });
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView symbolTextView;
        TextView usdprice, percentage;
        ImageView logo, graph;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txt1);
            symbolTextView = itemView.findViewById(R.id.subtxt1);
            usdprice = itemView.findViewById(R.id.usd);
            percentage = itemView.findViewById(R.id.percentage);
            logo = itemView.findViewById(R.id.logocrypto);
            graph = itemView.findViewById(R.id.graph);
        }
    }
}
