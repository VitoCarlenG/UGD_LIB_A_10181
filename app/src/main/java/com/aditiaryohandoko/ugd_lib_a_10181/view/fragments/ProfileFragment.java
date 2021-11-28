package com.aditiaryohandoko.ugd_lib_a_10181.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aditiaryohandoko.ugd_lib_a_10181.databinding.FragmentProfileBinding;
import com.aditiaryohandoko.ugd_lib_a_10181.view.MainViewModel;
import com.aditiaryohandoko.ugd_lib_a_10181.view.QRScannerActivity;


public class ProfileFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentProfileBinding binding;

    /**
     * TODO 3.2 isi logic untuk method onActivityResult()
     * di sini kalian ambil data dari result QRScannerActivity terus masukan ke method scanQRUserProfile()
     * milik viewmodel
     */
    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    // taruh kode disini
                                    Intent intent = result.getData();
                                    String strQRRes = intent.getStringExtra("QR_RESULT");

                                    viewModel.scanQRUserProfile(strQRRes);
                                } catch (Exception e) {
                                    Toast.makeText(requireContext(), "QR CODE TIDAK VALID!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * TODO 3.3 isi logic untuk method onViewCreated()
     * disini kalian tampilkan seluruh data yang disimpan dalam qr kedalam textview yang ada
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        viewModel.getUserProfileLiveData().observe(getViewLifecycleOwner(), data -> {

            if(data!=null) {
                binding.txtUsername.setText(data.getUsername());
                binding.txtFullname.setText(data.getFullname());
                binding.txtEmail.setText(data.getEmail());
                binding.txtPhone.setText(data.getPhone());
            }
        });

        binding.btnScan.setOnClickListener(v -> {
            cameraResult.launch(new Intent(requireActivity(), QRScannerActivity.class));
        });
    }
}