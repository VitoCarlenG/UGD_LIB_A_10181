package com.aditiaryohandoko.ugd_lib_a_10181.model;


import com.google.gson.annotations.SerializedName;

/**
 * TODO 1.2 Tambah Anotasi kelas YearlyPeak
 * Kelas Model untuk field peakYearlyOnlineUser di kelas Game yang diparse dari json file
 *
 * @HINT: mirip Guided tinggal kasih anotasi GSONnya :)
 */
public class YearlyPeak {
    @SerializedName("year")
    private int year;
    @SerializedName("value")
    private int value;

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }
}
