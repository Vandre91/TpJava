package fr.intech.s5.ws_hotel_android.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import fr.intech.s5.ws_hotel_android.model.Chambres;
import fr.intech.s5.ws_hotel_android.utils.HttpHelper;
import fr.intech.s5.ws_hotel_android.utils.RequestPackage;

/**
 * Created by Valentin Rigaud on 27/09/2017.
 */

public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";

    public static final String MY_INTENT_SERVICE_ID = "myIntentServiceID";
    public static final String MY_INTENT_SERVICE_MESSAGE = "myIntentServiceMessage";
    public static final String REQUEST_PACKAGE = "requestPackage";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RequestPackage requestPackage = intent.getParcelableExtra(REQUEST_PACKAGE);

        String response = "";
        try {
            response = HttpHelper.downloadFromFeed(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Chambres chambres = gson.fromJson(response, Chambres.class);
        if(chambres != null) {
            Intent messageIntent = new Intent(MY_INTENT_SERVICE_ID);
            messageIntent.putExtra(MY_INTENT_SERVICE_MESSAGE, chambres);
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
            manager.sendBroadcast(messageIntent);
        } else Toast.makeText(this, "Chambre réservée", Toast.LENGTH_SHORT).show();
    }
}
