package fr.intech.s5.tp.ws_rest_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import fr.intech.s5.tp.ws_rest_android.model.DataItem;
import fr.intech.s5.tp.ws_rest_android.service.MyIntentService;
import fr.intech.s5.tp.ws_rest_android.utils.NetworkHelper;
import fr.intech.s5.tp.ws_rest_android.utils.RequestPackage;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    private boolean networkOk;

    List<DataItem> mItemList;
    RecyclerView mRecyclerView;
    DataItemAdapter mItemAdapter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        DataItem[] dataItems = (DataItem[]) intent
                .getParcelableArrayExtra(MyIntentService.MY_INTENT_SERVICE_MESSAGE);

        Toast.makeText(
                MainActivity.this,
                "Reception de " + dataItems.length + " items délivrés par le service",
                Toast.LENGTH_SHORT
        ).show();

        mItemList = Arrays.asList(dataItems);
        displayDataItems();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvItems);

        networkOk = NetworkHelper.hasNetworkAccess(this);
        if( networkOk ) {
            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndPoint(JSON_URL);

            Intent intent = new Intent(this, MyIntentService.class);
            intent.putExtra(MyIntentService.REQUEST_PACKAGE, requestPackage);
            startService(intent);
        } else {
            Toast.makeText(this, "Réseau indisponible.", Toast.LENGTH_SHORT).show();
        }

        LocalBroadcastManager.getInstance(getApplicationContext())
            .registerReceiver(broadcastReceiver, new IntentFilter(MyIntentService.MY_INTENT_SERVICE_ID));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
            .unregisterReceiver(broadcastReceiver);
    }

    private void displayDataItems() {
        if( mItemList != null ) {
            mItemAdapter = new DataItemAdapter( this, mItemList );
            mRecyclerView.setAdapter( mItemAdapter );
        }
    }
}
