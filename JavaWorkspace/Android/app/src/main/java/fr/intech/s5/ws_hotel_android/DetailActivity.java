package fr.intech.s5.ws_hotel_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import fr.intech.s5.ws_hotel_android.model.ListChambre;
import fr.intech.s5.ws_hotel_android.service.MyIntentService;
import fr.intech.s5.ws_hotel_android.utils.RequestPackage;
import it.sephiroth.android.library.picasso.Picasso;

@SuppressWarnings("FieldCanBeLocal")
public class DetailActivity extends AppCompatActivity {

    private String RENT_URL = "http://10.8.111.55:8080/chambre/reservation/v1";

    private ListChambre chambre;

    private TextView tvName, tvDescription, tvPrice;
    private ImageView ivImage;
    private EditText etFrom, etTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        chambre = getIntent().getExtras().getParcelable(ChambresAdapter.ITEM_KEY);
        if (chambre == null) throw new AssertionError("Null data chambre received!");

        tvName = (TextView) findViewById(R.id.tvItemName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivImage = (ImageView) findViewById(R.id.ivImage);

        tvName.setText("Chambre " + chambre.getNumeroChambre());
        tvDescription.setText(chambre.getDescription());

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvPrice.setText(nf.format(chambre.getPrix()));

        try {
            Picasso.with(this).load(ChambresAdapter.PHOTOS_BASE_URL + chambre.getImage()).resizeByMaxSide().into(ivImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rentClickHandler(View view) {
        if (chambre == null) throw new AssertionError("Chambre can't be null!");

        etFrom = (EditText) findViewById(R.id.etFrom);
        etTo = (EditText) findViewById(R.id.etTo);

        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setEndPoint(RENT_URL);
        requestPackage.setMethod("POST");

        Map<String, String> params = new HashMap<>(3);
        params.put("chambreId", "" + chambre.getChambreId());
        params.put("dateDebut", etFrom.getText().toString());
        params.put("dateFin", etTo.getText().toString());
        requestPackage.setParams(params);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(MyIntentService.REQUEST_PACKAGE, requestPackage);
        startService(intent);
    }
}
