package fr.intech.s5.tp.ws_rest_android.utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hugoz on 25/09/2017.
 */

public class HttpHelper {

    public static String downloadFromFeed(RequestPackage requestPackage) throws IOException {
        String address = requestPackage.getEndpoint();
        String encodedParams = requestPackage.getEncodedParams();

        if( requestPackage.getMethod().equals("GET") && encodedParams.length() > 0 )
            address = String.format("%s?%s", address, encodedParams);

        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(address);

        if( requestPackage.getMethod().equals("POST") ) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            Map<String, String> params = requestPackage.getParams();

            for( String key : params.keySet() )
                builder.addFormDataPart(key, params.get(key) );

            RequestBody requestBody = builder.build();
            requestBuilder.method("POST", requestBody);
        }

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();

        if( response.isSuccessful() ) return response.body().string();
        else throw new IOException("Exception: response code is " + response.code());
    }
}
