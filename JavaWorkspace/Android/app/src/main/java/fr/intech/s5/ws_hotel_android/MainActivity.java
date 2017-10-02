package fr.intech.s5.ws_hotel_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import fr.intech.s5.ws_hotel_android.model.Chambres;
import fr.intech.s5.ws_hotel_android.model.ListChambre;
import fr.intech.s5.ws_hotel_android.service.MyIntentService;
import fr.intech.s5.ws_hotel_android.utils.NetworkHelper;
import fr.intech.s5.ws_hotel_android.utils.RequestPackage;

public class MainActivity extends AppCompatActivity {

    private static final String BACKEND_URL = "http://10.8.111.55:8080/chambre/reservation/v1";
    private boolean networkOk;

    Chambres mChambres;
    RecyclerView mRecyclerView;
    ChambresAdapter mChambresAdapter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Chambres chambres = (Chambres) intent
                .getParcelableExtra(MyIntentService.MY_INTENT_SERVICE_MESSAGE);

            Toast.makeText(
                    MainActivity.this,
                    "Reception d'un item du service",
                    Toast.LENGTH_SHORT
            ).show();

            mChambres = chambres;
            displayDataItems();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvChambres);

        networkOk = NetworkHelper.hasNetworkAccess(this);
        if( networkOk ) {
            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndPoint(BACKEND_URL);

            Intent intent = new Intent(this, MyIntentService.class);
            intent.putExtra(MyIntentService.REQUEST_PACKAGE, requestPackage);
            startService(intent);
        } else {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
        }

        LocalBroadcastManager.getInstance(getApplicationContext())
            .registerReceiver(
                broadcastReceiver,
                new IntentFilter(MyIntentService.MY_INTENT_SERVICE_ID)
            );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
            .unregisterReceiver(broadcastReceiver);
    }

    private void displayDataItems() {
        if( mChambres == null ) return;

        List<ListChambre> listChambres = mChambres.getListChambres();
        if( listChambres == null || listChambres.isEmpty() ) return;

        mChambresAdapter = new ChambresAdapter(this, listChambres);
        mRecyclerView.setAdapter( mChambresAdapter );
    }
}
