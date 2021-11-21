package com.aditiaryohandoko.ugd_lib_x_yyyyy.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.aditiaryohandoko.ugd_lib_x_yyyyy.model.Response;

import java.io.Reader;

import javax.inject.Inject;

public class MainRepository {

    private final Context context;

    @Inject
    public MainRepository(Context context) {
        this.context = context;
    }

    /**
     * TODO 1.5 isi logic untuk Method getResponse()
     *
     * @return MutableLiveData dari kelas response yang nanti disimpan di viewmodel
     * @throws Exception
     * @HINT: mirip guided tapi bungkus hasil keluaran dengan MutableLiveData
     * cara menggunakan live data ada didokumentasi resmi android google
     * https://developer.android.com/topic/libraries/architecture/livedata?hl=id
     */
    public MutableLiveData<Response> getResponse() throws Exception {
        return null;
    }

    /**
     * TODO 1.4 isi logic untuk Method loadJson()
     *
     * @return referensi file datagame.json dari folder assets
     * @throws Exception
     * @HINT: gunakan context yang ada dikelas ini agar bisa menggunakan method assets
     */
    private Reader loadJson() throws Exception {
        return null;
    }
}
