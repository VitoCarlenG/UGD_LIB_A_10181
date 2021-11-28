package com.aditiaryohandoko.ugd_lib_a_10181.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * TODO 1.0 Tambah Anotasi Kelas Game
 * Kelas Model untuk data game yang diparse dari json file
 *
 * @HINT: mirip Guided tinggal kasih anotasi GSONnya :)
 */
public class Game {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("urlImage")
    private String urlImage;
    @SerializedName("currency")
    private String currency;
    @SerializedName("price")
    private double price;
    @SerializedName("description")
    private String description;
    @SerializedName("peakYearlyOnlineUser")
    private List<YearlyPeak> peakYearlyOnlineUser;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * TODO 1.1 isi logic untuk Method getFormattedPrice()
     *
     * @return String gabungan currency dan price
     * @HINT: kalo harganya 0 balikin string Free to Play
     */
    public String getFormattedPrice() {
        if(getPrice()==0){
            return "Free to Play";
        }
        else{
            return getCurrency() + getPrice();
        }
    }

    public String getDescription() {
        return description;
    }

    public List<YearlyPeak> getPeakYearlyOnlineUser() {
        return peakYearlyOnlineUser;
    }
}
