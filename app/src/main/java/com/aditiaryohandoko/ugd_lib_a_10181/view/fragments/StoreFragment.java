package com.aditiaryohandoko.ugd_lib_a_10181.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aditiaryohandoko.ugd_lib_a_10181.adapter.AdapterCardGame;
import com.aditiaryohandoko.ugd_lib_a_10181.adapter.OnCardClickListener;
import com.aditiaryohandoko.ugd_lib_a_10181.databinding.FragmentStoreBinding;
import com.aditiaryohandoko.ugd_lib_a_10181.model.Game;
import com.aditiaryohandoko.ugd_lib_a_10181.view.MainViewModel;
import com.google.gson.Gson;

import java.util.List;

public class StoreFragment extends Fragment implements OnCardClickListener {

    private FragmentStoreBinding binding;
    private MainViewModel viewModel;

    private AdapterCardGame adapter;

    private OnCardClickListener listener;

    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * TODO 1.7 isi logic untuk method onViewCreated()
     * disini kalian hanya perlu mengobserve gameListLiveData dari viewmodel lalu panggil method
     * setupRecyclerview() didalamnya.
     *
     * @param view
     * @param savedInstanceState
     *
     * TODO BONUS 1.1 tambahkan queryTextListener untuk serchview
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.loadResponse();

        viewModel.getGameListLiveData().observe(getViewLifecycleOwner(), this::setupRecyclerView);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }

    /**
     * TODO 1.6 isi logic untuk Method setupRecyclerView()
     * method ini digunakan untuk membuat dan memasukan adapter ke recyclerview
     *
     * @param data
     * @HINT: panggil method ini sewaktu live data sedang diobserve
     */
    private void setupRecyclerView(List<Game> data) {
        adapter = new AdapterCardGame(data, this);
        binding.rvStorePage.setAdapter(adapter);
        binding.rvStorePage.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Game game) {
        viewModel.addToCart(game);
        Toast.makeText(getContext(), "Game Behasil ditambahkan", Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO 1.9 isi logic untuk Method onChartClick()
     * Method ini digunakan untuk memanggil dialogfragment yang akan menampilkan chart dari
     * jumlah pemain tertinggi setiap tahun.
     *
     * @param game
     * @HINT: gunakan FragmentManager untuk berpindah fragment dengan menambahkan argument bundle yang
     * menyimpan String hasil keluaran GSON dari list peakYearlyOnlineUser.
     */
    @Override
    public void onChartClick(Game game) {
        FragmentManager fragmentManager = getParentFragmentManager();
        ChartDialogFragment chartDialogFragment = new ChartDialogFragment();
        chartDialogFragment.show(fragmentManager, "chartDialogFragment");

        Bundle args = new Bundle();
        Gson gson = new Gson();
        args.putString("game", gson.toJson(game.getPeakYearlyOnlineUser()));
        chartDialogFragment.setArguments(args);
    }

    /**
     * TODO 1.8 isi logic untuk Method onDescriptionCLick
     * Method ini digunakan untuk memanggil dialogfragment yang menampilkan deskripsi dari game
     *
     * @param desc
     * @HINT: gunakan FragmentManager untuk berpindah fragment dengan menambahkan argument bundle yang
     * menyimpan string deskripsi
     */
    @Override
    public void onDescriptionClick(String desc) {
        FragmentManager fragmentManager = getParentFragmentManager();
        DescriptionDialogFragment descriptionDialogFragment = new DescriptionDialogFragment();
        descriptionDialogFragment.show(fragmentManager, "descriptionDialogFragment");

        Bundle args = new Bundle();
        args.putString("desc", desc);
        descriptionDialogFragment.setArguments(args);
    }
}