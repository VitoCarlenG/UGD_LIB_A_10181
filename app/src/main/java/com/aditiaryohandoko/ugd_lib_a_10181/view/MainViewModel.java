package com.aditiaryohandoko.ugd_lib_a_10181.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aditiaryohandoko.ugd_lib_a_10181.model.Game;
import com.aditiaryohandoko.ugd_lib_a_10181.model.PurchasedGame;
import com.aditiaryohandoko.ugd_lib_a_10181.model.Response;
import com.aditiaryohandoko.ugd_lib_a_10181.model.UserProfile;
import com.aditiaryohandoko.ugd_lib_a_10181.repositories.MainRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private MainRepository repo;

    private MutableLiveData<List<Game>> gameListLiveData;
    private MutableLiveData<String> errMsg;

    private MutableLiveData<List<PurchasedGame>> keranjangBelanja;

    private MutableLiveData<UserProfile> userProfileLiveData;

    private boolean checkQR = false;

    {
        gameListLiveData = new MutableLiveData<>(new ArrayList<>());
        keranjangBelanja = new MutableLiveData<>(new ArrayList<>());
        userProfileLiveData = new MutableLiveData<>(null);
        errMsg = new MutableLiveData<>("");
    }

    @Inject
    public MainViewModel(MainRepository repo) {
        this.repo = repo;
    }

    public void loadResponse() {
        try {
            if (this.gameListLiveData.getValue().size() == 0) {
                LiveData<Response> temp = this.repo.getResponse();

                if (temp == null || temp.getValue() == null || temp.getValue().getData().isEmpty())
                    throw new Exception("Data gagal diambil");

                this.gameListLiveData.setValue(temp.getValue().getData());
                this.errMsg.setValue(temp.getValue().getMessage() + " size: " + temp.getValue().getData().size());
            }

        } catch (Exception e) {
            e.printStackTrace();
            errMsg.setValue(e.getMessage());
        }
    }

    /**
     * TODO 3.1 isi logic untuk method scanQRUserProfile()
     * @param jsonString
     * @throws Exception
     * @HINT: gunakan GSON untuk parse jsonString dengan kelas model UserProfile lalu tampung di
     * userProfileLiveData
     */
    public void scanQRUserProfile(String jsonString) throws Exception {
        try {
            Gson gson = new Gson();

            UserProfile userProfile = gson.fromJson(jsonString, UserProfile.class);
            userProfileLiveData.setValue(userProfile);

            checkQR = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO 1.12 isi logic untuk Method addToCart()
     * Method ini digunakan ketika mau menambahkan game diawal jadi saat milih game dari
     * StoreFragment buat dimasukin ke CartFragment
     *
     * @param game
     * @HINT: gunakan method cariGame() untuk cek apakah game yang dipilih dari storeFragment
     * sudah ada didalam Cart.
     */
    public void addToCart(Game game) {
        PurchasedGame purchasedGame = cariGame(game.getId());

        if(purchasedGame != null) {
            purchasedGame.setJumlah(purchasedGame.getJumlah() + 1);
        }else{
            if(keranjangBelanja.getValue() != null) {
                keranjangBelanja.getValue().add(new PurchasedGame(game, 1));
            }
        }
        keranjangBelanja.setValue(keranjangBelanja.getValue());
    }

    /**
     * TODO BONUS 2.0 isi logic untuk method clearCart
     * Method ini digunakan untuk clearing cart setelah pdf digeneraete
     */
    public void clearCart() {
    }

    /**
     * TODO BONUS 2.3 isi logic untuk method addOneFromCart()
     * Method ini digunakan untuk button tambah di cartFragment
     * @param game
     * @HINT: gunakan method cariGame() untuk mendapat referensi game yang dimaksud
     */
    public void addOneFromCart(Game game) {

    }

    /**
     * TODO BONUS 2.4 isi logic untuk method removeFromCart()
     * Method ini digunakan untuk button kurang di cartFragment
     * @param game
     */
    public void removeFromCart(Game game) {

    }

    private PurchasedGame cariGame(int id) {
        if (keranjangBelanja.getValue() != null) {
            List<PurchasedGame> belanjaan = keranjangBelanja.getValue();
            for (PurchasedGame game : belanjaan) {
                if (game.getGame().getId() == id)
                    return game;
            }
        }
        return null;
    }

    public LiveData<List<Game>> getGameListLiveData() {
        return gameListLiveData;
    }

    public LiveData<List<PurchasedGame>> getKeranjangBelanja() {
        return keranjangBelanja;
    }

    public LiveData<String> getErrMsg() {
        return errMsg;
    }

    public LiveData<UserProfile> getUserProfileLiveData() {
        return userProfileLiveData;
    }

    public boolean getCheckQR() {
        return checkQR;
    }

    public void setCheckQR(boolean checkQR) {
        this.checkQR = checkQR;
    }
}
