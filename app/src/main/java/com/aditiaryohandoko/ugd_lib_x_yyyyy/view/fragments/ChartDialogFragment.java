package com.aditiaryohandoko.ugd_lib_x_yyyyy.view.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.aditiaryohandoko.ugd_lib_x_yyyyy.databinding.FragmentChartDialogBinding;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.charts.Cartesian;

public class ChartDialogFragment extends DialogFragment {

    private FragmentChartDialogBinding binding;

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * TODO 1.11 isi logic untuk kelas onCreateDialog
     * di bagian ini kalian perlu ambil data peakYearlyOnlineUser dari bundle lalu gunaain GSON untuk menjadikan
     * data menjadi list kembali. Untuk jenis chartnya kalian bebas menggunakan segala jenis chart yang disediakan library.
     *
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = FragmentChartDialogBinding.inflate(LayoutInflater.from(requireContext()));

        APIlib.getInstance().setActiveAnyChartView(binding.myChart);

        binding.myChart.setProgressBar(binding.progressBar);
        Cartesian cartesian = AnyChart.column();

        binding.myChart.setChart(cartesian);

        return new AlertDialog.Builder(requireActivity()).setView(binding.getRoot()).create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}