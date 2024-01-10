package com.example.cryptocurrencytask;

import com.google.gson.annotations.SerializedName;

public class CoinMetadataResponse {
    @SerializedName("data")
    private MetadataData data;

    public MetadataData getData() {
        return data;
    }

    public static class MetadataData {
        @SerializedName("cryptoCurrency")
        private CryptoCurrency cryptoCurrency;

        public CryptoCurrency getCryptoCurrency() {
            return cryptoCurrency;
        }
    }

    public static class CryptoCurrency {
        @SerializedName("logo")
        private String logo;

        public String getLogo() {
            return logo;
        }
    }
}
