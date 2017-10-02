package fr.intech.s5.ws_hotel_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Chambres implements Parcelable {

    private List<ListChambre> listChambres = null;

    public List<ListChambre> getListChambres() {
        return listChambres;
    }

    public void setListChambres(List<ListChambre> listChambres) {
        this.listChambres = listChambres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.listChambres);
    }

    public Chambres() {
    }

    protected Chambres(Parcel in) {
        this.listChambres = in.createTypedArrayList(ListChambre.CREATOR);
    }

    public static final Parcelable.Creator<Chambres> CREATOR = new Parcelable.Creator<Chambres>() {
        @Override
        public Chambres createFromParcel(Parcel source) {
            return new Chambres(source);
        }

        @Override
        public Chambres[] newArray(int size) {
            return new Chambres[size];
        }
    };
}