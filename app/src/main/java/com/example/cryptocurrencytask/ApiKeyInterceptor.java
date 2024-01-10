package com.example.cryptocurrencytask;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    private static final String API_KEY = "d4296abe-bfee-4ca4-bc5c-78be225506db"; // Replace with your actual API key

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("X-CMC_PRO_API_KEY", API_KEY)
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}

