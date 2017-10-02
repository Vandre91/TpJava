package fr.intech.s5.tp.ws_rest_android;

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
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.intech.s5.tp.ws_rest_android.model.DataItem;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Cette classe hérite de la classe RecycleView :
 * Une RecyclerView est une nouvelle façon d’afficher une liste ou une grille de vues.
 *      Elle peut s’apparenter à une ListView mais permet beaucoup plus de personnalisation.
 *      A la même façon que les ListView, il faut créer un Adapter, mais cette fois ci il doit étendre RecyclerView.Adapter<>.
 *      Dans notre cas ça sera RecycleView.Adapter<DataItemAdapter.ViewHolder>.
 *      ViewHolder étant une classe qui va fonctionner avec notre Adapter.
 *      Cet adapter est typé pour un fonctionner avec une certaine classe de ViewHolder.
 *      Un adapter est un objet qui va garder les références vers les vues de chaque cellule.
 *      Dans notre cas il va donc falloir créer un ViewHolder. nous le nommerons ViewHolder
 */
public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    //Tag
    private static final String TAG = "DataItemAdapter";
    //Clé qui permettra d’identifier un item en particulier
    public static final String ITEM_ID_KEY = "item_id_key";
    //Clé qui permettra d’identifier les objets du dataItemAdapter qui seront transmis dans l’application à travers les intents
    public static final String ITEM_KEY = "item_key";
    private List<DataItem> mItems;
    private Map<String, Bitmap> mBitmaps = new HashMap<>();
    private Context mContext;
    private SharedPreferences.OnSharedPreferenceChangeListener prefsListener;
    //Url vers les images. Une référence vers chaque image est retourné par le web service.
    private static final String PHOTOS_BASE_URL =
            "http://560057.youcanlearnit.net/services/images/";

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(mContext);
        prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
                Log.i("preferences", "onSharedPreferenceChanged: " + key);
            }
        };
        settings.registerOnSharedPreferenceChangeListener(prefsListener);

        LayoutInflater inflater = LayoutInflater.from(mContext);
//Initialisation d’un item avec list_item
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque item
    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final DataItem item = mItems.get(position);

        //Debut bloc recupération d'image
        try {
            holder.tvName.setText(item.getItemName());
            String url = PHOTOS_BASE_URL + item.getImage();

            //récupération et traitement de l'image par picasso
            Picasso.with(mContext)
                    .load(url)
                    .resize(50, 50)
                    .into(holder.imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //fin bloc récupération image

        //lorsqu'on clique sur un item, les informations concernant l'item sont récupéré
        //puis transmit à la vue des détails à travers un intent
        // la clé ITEM_KEM permettra de récupérer les informations d'un item en particulier
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });

        //lorsqu'on fait un clic long sur l'item cette méthode sera traité.
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "Vous avez fait un appui long sur l'item" + item.getItemName(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    //le viewholder associé à notre adapter pour garder une référence vers l'item sur lequel on a cliqué
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public ImageView imageView;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.itemNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}