package com.aditiaryohandoko.ugd_lib_x_yyyyy.model;

import java.util.List;

/**
 * TODO 1.0 Tambah Anotasi Kelas Game
 * Kelas Model untuk data game yang diparse dari json file
 *
 * @HINT: mirip Guided tinggal kasih anotasi GSONnya :)
 */
public class Game {

    private int id;

    private String title;

    private String urlImage;

    private String currency;

    private double price;

    private String description;

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
        return "";
    }

    public String getDescription() {
        return description;
    }

    public List<YearlyPeak> getPeakYearlyOnlineUser() {
        return peakYearlyOnlineUser;
    }
}
