package com.aditiaryohandoko.ugd_lib_a_10181.adapter;

import com.aditiaryohandoko.ugd_lib_a_10181.model.PurchasedGame;

public interface OnCartClickListener {
    void onTambah(PurchasedGame purchasedGame, int position);
    void onKurang(PurchasedGame purchasedGame, int position);
}
