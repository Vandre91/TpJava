package fr.intech.s5.ws_hotel_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.intech.s5.ws_hotel_android.model.ListChambre;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Valentin Rigaud on 27/09/2017.
 */

public class ChambresAdapter extends RecyclerView.Adapter<ChambresAdapter.ViewHolder> {

    private static final String TAG = "ChambresAdapter";
    public static final String ITEM_ID_KEY = "item_id_key";
    public static final String ITEM_KEY = "item_key";
    private List<ListChambre> mChambres;
    private Map<String, Bitmap> mBitmaps = new HashMap<>();
    private Context mContext;
    private SharedPreferences.OnSharedPreferenceChangeListener prefsListener;
    public static final String PHOTOS_BASE_URL = "http://10.8.111.55:8080/images/";

    public ChambresAdapter(Context context, List<ListChambre> chambres) {
        this.mContext = context;
        this.mChambres = chambres;
    }

    @Override
    public ChambresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i("preferences", "onSharedPrefrenceChanged: " + key);
            }
        };
        settings.registerOnSharedPreferenceChangeListener(prefsListener);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListChambre item = mChambres.get(position);

        try {
            holder.itemName.setText("Chambre " + item.getNumeroChambre());
            String url = PHOTOS_BASE_URL + item.getImage();

            Picasso.with(mContext).load(url).resize(50, 50).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChambres.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public ImageView imageView;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.itemNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}
