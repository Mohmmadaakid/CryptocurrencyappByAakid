package com.example.cryptocurrencytask;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinMarketCapResponse {

    @SerializedName("data")
    private List<CryptoData> data;

    public List<CryptoData> getData() {
        return data;
    }

    public static class CryptoData {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("symbol")
        private String symbol;

        @SerializedName("slug")
        private String slug;

        @SerializedName("cmc_rank")
        private int cmcRank;

        @SerializedName("num_market_pairs")
        private int numMarketPairs;

        @SerializedName("circulating_supply")
        private double circulatingSupply;

        @SerializedName("total_supply")
        private double totalSupply;

        @SerializedName("max_supply")
        private double maxSupply;

        @SerializedName("infinite_supply")
        private boolean infiniteSupply;

        @SerializedName("last_updated")
        private String lastUpdated;

        @SerializedName("date_added")
        private String dateAdded;

        @SerializedName("tags")
        private List<String> tags;

        @SerializedName("platform")
        private Object platform;

        @SerializedName("self_reported_circulating_supply")
        private Object selfReportedCirculatingSupply;

        @SerializedName("self_reported_market_cap")
        private Object selfReportedMarketCap;

        @SerializedName("quote")
        private Quote quote;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getSlug() {
            return slug;
        }

        public int getCmcRank() {
            return cmcRank;
        }

        public int getNumMarketPairs() {
            return numMarketPairs;
        }

        public double getCirculatingSupply() {
            return circulatingSupply;
        }

        public double getTotalSupply() {
            return totalSupply;
        }

        public double getMaxSupply() {
            return maxSupply;
        }

        public boolean isInfiniteSupply() {
            return infiniteSupply;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public List<String> getTags() {
            return tags;
        }

        public Object getPlatform() {
            return platform;
        }

        public Object getSelfReportedCirculatingSupply() {
            return selfReportedCirculatingSupply;
        }

        public Object getSelfReportedMarketCap() {
            return selfReportedMarketCap;
        }

        public Quote getQuote() {
            return quote;
        }
    }

    public static class Quote {

        @SerializedName("USD")
        private USD usd;

        public USD getUsd() {
            return usd;
        }
    }

    public static class USD {

        @SerializedName("price")
        private double price;

        @SerializedName("volume_24h")
        private double volume24h;

        @SerializedName("volume_change_24h")
        private double volumeChange24h;

        @SerializedName("percent_change_1h")
        private double percentChange1h;

        @SerializedName("percent_change_24h")
        private double percentChange24h;

        @SerializedName("percent_change_7d")
        private double percentChange7d;

        @SerializedName("market_cap")
        private double marketCap;

        @SerializedName("market_cap_dominance")
        private double marketCapDominance;

        @SerializedName("fully_diluted_market_cap")
        private double fullyDilutedMarketCap;

        @SerializedName("last_updated")
        private String lastUpdated;

        public double getPrice() {
            return price;
        }

        public double getVolume24h() {
            return volume24h;
        }

        public double getVolumeChange24h() {
            return volumeChange24h;
        }

        public double getPercentChange1h() {
            return percentChange1h;
        }

        public double getPercentChange24h() {
            return percentChange24h;
        }

        public double getPercentChange7d() {
            return percentChange7d;
        }

        public double getMarketCap() {
            return marketCap;
        }

        public double getMarketCapDominance() {
            return marketCapDominance;
        }

        public double getFullyDilutedMarketCap() {
            return fullyDilutedMarketCap;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }
    }
}

