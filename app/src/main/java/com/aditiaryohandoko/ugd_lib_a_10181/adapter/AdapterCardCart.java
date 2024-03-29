package com.aditiaryohandoko.ugd_lib_a_10181.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiaryohandoko.ugd_lib_a_10181.R;
import com.aditiaryohandoko.ugd_lib_a_10181.databinding.AdapterCartBinding;
import com.aditiaryohandoko.ugd_lib_a_10181.model.Game;
import com.aditiaryohandoko.ugd_lib_a_10181.model.PurchasedGame;
import com.aditiaryohandoko.ugd_lib_a_10181.model.UserProfile;
import com.aditiaryohandoko.ugd_lib_a_10181.repositories.MainRepository;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
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
            binding.title.setText(purchasedGame.getGame().getTitle());
            binding.price.setText(purchasedGame.getGame().getCurrency() + purchasedGame.getTotalBayar());
            binding.jumlahBeli.setText(String.valueOf(purchasedGame.getJumlah()));

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .dontAnimate()
                    .dontTransform()
                    .placeholder(R.drawable.ic_launcher_background)
                    .priority(Priority.IMMEDIATE)
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .format(DecodeFormat.DEFAULT);

            Glide.with(binding.getRoot())
                    .applyDefaultRequestOptions(requestOptions)
                    .load(purchasedGame.getGame().getUrlImage())
                    .into(binding.thumbNail);
        }
    }
}
