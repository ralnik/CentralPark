package ru.ralnik.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;

import ru.ralnik.centralpark.R;

@Entity(tableName = "flats")
public class Flat implements Serializable {
    @PrimaryKey(autoGenerate = false)
//    @NonNull
//    @ColumnInfo(name = "_id")
//    private Long id;

    @SerializedName("id_flat")
    @NonNull
    @ColumnInfo(name = "_id")
    private String  id;              //ID квартиры

    @ColumnInfo(name = "nom_kv")
    private int  nom_kv;         //номер квартиры

    @ColumnInfo(name = "corpus")
    private int  corpus;              //корпус

    @ColumnInfo(name = "etag")
    private int etag;               //этаж

    @ColumnInfo(name = "comnat")
    private int comnat;          //Кол-во комнат

    @ColumnInfo(name = "ploshad")
    private Float ploshad;          //Площадь

    @ColumnInfo(name = "price")
    private Float price;          //цена

    @ColumnInfo(name = "status")
    private int status;          //статус квартиры:
                                //        0: продано
	                            //        1: бронь
                                //        2: свободно

    @ColumnInfo(name = "planirovka")
    private String planirovka;          //ссылка на планировку

    @ColumnInfo(name = "favorite")
    private int favorite;

    public Flat(){
    }

    public Flat(String id, int nom_kv, int corpus, int etag, int comnat, Float ploshad, Float price, int status) {
        this.id = id;
        this.nom_kv = nom_kv;
        this.corpus = corpus;
        this.etag = etag;
        this.comnat = comnat;
        this.ploshad = ploshad;
        this.price = price;
        this.status = status;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
//    @NonNull
//    public String getId_flat() {
//        return id_flat;
//    }
//
//    public void setId_flat(String id_flat) {
//        this.id_flat = id_flat;
//    }

    public int getNom_kv() {
        return nom_kv;
    }

    public void setNom_kv(int nom_kv) {
        this.nom_kv = nom_kv;
    }

    public int getCorpus() {
        return corpus;
    }

    public void setCorpus(int corpus) {
        this.corpus = corpus;
    }

    public int getEtag() {
        return etag;
    }

    public void setEtag(int etag) {
        this.etag = etag;
    }

    public int getComnat() {
        return comnat;
    }

    public void setComnat(int comnat) {
        this.comnat = comnat;
    }

    public Float getPloshad() {
        return ploshad;
    }

    public void setPloshad(Float ploshad) {
        this.ploshad = ploshad;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlanirovka() {
        return planirovka;
    }

    public void setPlanirovka(String planirovka) {
        this.planirovka = planirovka;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public static String makePrettyCost(String cost){
        long realcost=0;
        realcost = Math.round(Double.valueOf(cost));

        String tempCost =  String.valueOf(realcost);
        String pattern = null;
        switch (tempCost.length()){
            case 6:
                pattern = "###,###";
                break;
            case 7:
                pattern = "#,###,###";
                break;
            case 8:
                pattern = "##,###,###";
                break;
            case 9:
                pattern = "###,###,###";
                break;
            case 10:
                pattern = "#,###,###,###";
                break;
            default:
                pattern = "###";
                break;
        }
        DecimalFormat mf = new DecimalFormat(pattern);
        tempCost = mf.format(realcost);
        return tempCost.replace(","," ");
    }

    public String getCorrectStatus(){
        switch (getStatus()){
            case 0:
                return "Продано";

            case 1:
                return "Забронированно";

            case 2:
                return "В продаже";

            default:
                return status+"";
        }
    }

    public String getNameCountRoomsFlat(){
        switch (getComnat()){
            case 1:
                return "ОДНОКОМНАТНАЯ КВАРТИРА";
            case 2:
                return "ДВУХКОМНАТНАЯ КВАРТИРА";
            case 3:
                return "ТРЕХКОМНАТНАЯ КВАРТИРА";
            case 4:
                return "ЧЕТЫРЕХКОМНАТНАЯ КВАРТИРА";
            case 5:
                return "ПЯТИКОМНАТНАЯ КВАРТИРА";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "id_flat=" + id + "&" +
                "nom_kv=" + nom_kv + "&" +
                "corpus=" + corpus + "&" +
                "etag=" + etag + "&" +
                "comnat=" + comnat + "&" +
                "ploshad=" + ploshad + "&" +
                "price=" + price + "&" +
                "status=" + status + "&" +
                "planirovka=" + planirovka;
    }
}
