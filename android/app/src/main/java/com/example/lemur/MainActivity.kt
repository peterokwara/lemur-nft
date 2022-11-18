package com.example.lemur

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGetBalance
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger
import java.net.ConnectException


class MainActivity : AppCompatActivity() {

    // inside of any of your application's code
    var walletAddress: String = BuildConfig.WALLET_ADDRESS;
    var contractAddress: String = BuildConfig.CONTRACT_ADDRESS;
    var webSocketUrl: String = BuildConfig.WEB_SOCKET_URL;
    var seed: String = BuildConfig.SEED;
    var tokenUri: String = BuildConfig.TOKEN_URI;
    private lateinit var credentials: Credentials;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Mint an nft when the mint button is clicked.
     */
    fun mintNft(view: View) {
        try {
            loadCredentials();
            val web3j: Web3j = createWeb3j()

            val nft: LemurNFT = LemurNFT.load(contractAddress, web3j, credentials, DefaultGasProvider())
            val transactionReceipt: TransactionReceipt = nft.mintNFT(walletAddress, tokenUri).send()

            Log.e("Response", transactionReceipt.toString());
            showResponse(transactionReceipt.toString());

        } catch (e: Exception){
            e.printStackTrace();
        }
        return;
    }

    /**
     * Display the response as a toast notification.
     */
    private fun showResponse(response: String){

        val duration  = Toast.LENGTH_LONG;
        val toast = Toast.makeText(applicationContext, response, duration);
        toast.show();

    }

    /**
     * Create a web3j web socket instance from the web socket url.
     */
    private fun createWeb3j(): Web3j {
        val webSocketService = WebSocketService(webSocketUrl, true)
        try {
            webSocketService.connect()
        } catch (e: ConnectException) {
            e.printStackTrace()
        }
        return Web3j.build(webSocketService)
    }

    /***
     * Create credentials from a seed.
     */
    private fun loadCredentials(){
        val masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(seed, ""))
        val path = intArrayOf(44 or HARDENED_BIT, 60 or HARDENED_BIT, HARDENED_BIT, 0, 0)
        val x = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)
        this.credentials = Credentials.create(x)
    }

}