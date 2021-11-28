package com.aditiaryohandoko.ugd_lib_a_10181.adapter;

import com.aditiaryohandoko.ugd_lib_a_10181.model.Game;

public interface OnCardClickListener {
    void onClick(Game game);

    void onChartClick(Game game);

    void onDescriptionClick(String desc);
}
