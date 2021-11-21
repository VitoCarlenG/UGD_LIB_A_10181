package com.aditiaryohandoko.ugd_lib_x_yyyyy.view.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aditiaryohandoko.ugd_lib_x_yyyyy.R;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.adapter.AdapterCardCart;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.adapter.OnCardClickListener;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.adapter.OnCartClickListener;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.databinding.FragmentCartBinding;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.model.PurchasedGame;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.model.UserProfile;
import com.aditiaryohandoko.ugd_lib_x_yyyyy.view.MainViewModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CartFragment extends Fragment implements OnCartClickListener {

    private FragmentCartBinding binding;
    private MainViewModel viewModel;

    private AdapterCardCart adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * TODO 2.4 isi logic untuk method onViewCreate()
     * tambahkan onclicklistener untuk button cetakNota yang nanti akan memanggil method createPdf()
     * berikan pengecekan apakah sudah scan qr.
     *
     * @param view
     * @param savedInstanceState
     *
     * TODO BONUS 2.1 panggil method clearCart setelah pdf berhasil dibuat.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        viewModel.getKeranjangBelanja().observe(getViewLifecycleOwner(), data -> {
            setupAdapter(data);
            if (data.size() == 0) {
                binding.cetakNota.setVisibility(View.GONE);
                binding.textKosong.setVisibility(View.VISIBLE);
            } else {
                binding.cetakNota.setVisibility(View.VISIBLE);
                binding.textKosong.setVisibility(View.GONE);
            }
        });

        binding.cetakNota.setOnClickListener(v -> {

        });
    }

    /**
     * TODO 2.2 isi logic untuk Method cetakPDF()
     * method ini digunakan untuk membuat pdf berdasarkan data game yang dibeli
     * (keranjangBelanja dikelas viewmodel)
     * jangan lupa pada bagian nama pembeli di set sesuai dengan nama pembeli di QR
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void cetakPdf() throws FileNotFoundException, DocumentException {


    }

    /**
     * TODO 2.3 isi logic untuk Method previewPDF()
     * method ini digunakan untuk menampilkan pdf setelah digenerate
     *
     * @param pdfFile
     * @HINT: apabila saat dijalankan method ini terlihat seperti tidak berjalan coba cek apakah
     * hp kalian memiliki aplikasi untuk membuka pdf.
     */
    private void previewPdf(File pdfFile) {

    }


    /**
     * TODO 2.1 isi logic untuk method setupAdapter()
     * method ini digunakan untuk membuat dan memasukan adapter ke recyclerview
     *
     * @param purchasedGameList
     */
    private void setupAdapter(List<PurchasedGame> purchasedGameList) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }

    @Override
    public void onTambah(PurchasedGame purchasedGame, int position) {
        viewModel.addOneFromCart(purchasedGame.getGame());
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onKurang(PurchasedGame purchasedGame, int position) {
        viewModel.removeFromCart(purchasedGame.getGame());
        adapter.notifyItemChanged(position);
    }

}