package fr.intech.s5.ws_hotel_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ListChambre implements Parcelable {

    private int chambreId;
    private int numeroChambre;
    private int prix;
    private List<Object> listeDesReservations = null;
    private String image;
    private String description;

    public int getChambreId() {
        return chambreId;
    }

    public void setChambreId(int chambreId) {
        this.chambreId = chambreId;
    }

    public int getNumeroChambre() {
        return numeroChambre;
    }

    public void setNumeroChambre(int numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public List<Object> getListeDesReservations() {
        return listeDesReservations;
    }

    public void setListeDesReservations(List<Object> listeDesReservations) {
        this.listeDesReservations = listeDesReservations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.chambreId);
        dest.writeInt(this.numeroChambre);
        dest.writeInt(this.prix);
        dest.writeList(this.listeDesReservations);
        dest.writeString(this.image);
        dest.writeString(this.description);
    }

    public ListChambre() {
    }

    protected ListChambre(Parcel in) {
        this.chambreId = in.readInt();
        this.numeroChambre = in.readInt();
        this.prix = in.readInt();
        this.listeDesReservations = new ArrayList<Object>();
        in.readList(this.listeDesReservations, Object.class.getClassLoader());
        this.image = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<ListChambre> CREATOR = new Parcelable.Creator<ListChambre>() {
        @Override
        public ListChambre createFromParcel(Parcel source) {
            return new ListChambre(source);
        }

        @Override
        public ListChambre[] newArray(int size) {
            return new ListChambre[size];
        }
    };
}