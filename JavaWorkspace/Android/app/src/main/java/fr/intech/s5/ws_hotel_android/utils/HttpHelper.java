package fr.intech.s5.ws_hotel_android.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Valentin Rigaud on 25/09/2017.
 */

public class HttpHelper {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String downloadFromFeed(RequestPackage requestPackage) throws IOException {
        String address = requestPackage.getEndpoint();
        String encodedParams = requestPackage.getEncodedParams();

        if( requestPackage.getMethod().equals("GET") && encodedParams.length() > 0 )
            address = String.format("%s?%s", address, encodedParams);

        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(address);

        if( requestPackage.getMethod().equals("POST") ) {
            RequestBody requestBody = RequestBody.create( JSON, requestPackage.getJSONParams() );
            requestBuilder.post( requestBody );
        }

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();

        if( response.isSuccessful() ) return response.body().string();
        else throw new IOException("Exception: response code is " + response.code());
    }
}
