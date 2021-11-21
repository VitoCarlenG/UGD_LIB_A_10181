package com.aditiaryohandoko.ugd_lib_x_yyyyy.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiaryohandoko.ugd_lib_x_yyyyy.databinding.AdapterCartBinding;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.model.PurchasedGame;

import java.util.List;

public class AdapterCardCart extends RecyclerView.Adapter<AdapterCardCart.CartViewHolder> {

    private List<PurchasedGame> purchasedGameList;
    private OnCartClickListener listener;

    public AdapterCardCart(List<PurchasedGame> purchasedGameList, OnCartClickListener listener) {
        this.purchasedGameList = purchasedGameList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(
                AdapterCartBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent, false
                ), listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(purchasedGameList.get(position));
    }

    @Override
    public int getItemCount() {
        return purchasedGameList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private AdapterCartBinding binding;
        private OnCartClickListener lister;

        public CartViewHolder(AdapterCartBinding binding, OnCartClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.lister = listener;
        }

        /**
         * TODO 2.0 isi logic untuk Method Bind()
         * Method ini digunakan untuk bind data ke adapter_cart.xml
         *
         * @param purchasedGame
         * @HINT: method bind biasa seperti guided, kalo buat onclick interface udah ada contoh di adapter yang laen
         * referensi lain: https://www.youtube.com/watch?v=69C1ljfDvl0&ab_channel=CodingWithMitch
         *
         * TODO BONUS 2.2 tambahkan logic untuk button tambah dan kurang
         */
        public void bind(PurchasedGame purchasedGame) {

        }
    }
}
