package com.example.lemur

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.Utf8String
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.gas.DefaultGasProvider
import java.net.ConnectException


class MainActivity : AppCompatActivity() {

    // inside of any of your application's code
    var walletAddress: String = BuildConfig.WALLET_ADDRESS;
    var contractAddress: String = BuildConfig.CONTRACT_ADDRESS;
    var webSocketUrl: String = BuildConfig.WEB_SOCKET_URL;
    var seed: String = BuildConfig.SEED;
    private lateinit var credentials: Credentials;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mintNft(view: View) {
        try {
            loadCredentials();
            val tokenURI = "ipfs://QmYueiuRNmL4MiA2GwtVMm6ZagknXnSpQnB3z2gWbz36hP"
            val web3j: Web3j = createWeb3j()

            val nft: LemurNFT = LemurNFT.load(contractAddress, web3j, credentials, DefaultGasProvider())
            val result: TransactionReceipt = nft.mintNFT(walletAddress, tokenURI).send()
            Log.e("Response", result.toString())

        } catch (e: Exception){
            e.printStackTrace();
        }
        return;
    }

    private fun createWeb3j(): Web3j {
        val webSocketService = WebSocketService(webSocketUrl, true)
        try {
            webSocketService.connect()
        } catch (e: ConnectException) {
            e.printStackTrace()
        }
        return Web3j.build(webSocketService)
    }

    fun EthCall.getTokenCount(outputParams: List<TypeReference<Type<*>>>): Type<*>? {
        return try {
            FunctionReturnDecoder.decode(
                value, outputParams
            ).firstOrNull()
        } catch (e: Exception) {
            null
        }
    }

    fun loadCredentials(){
        val masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(seed, ""))
        val path = intArrayOf(44 or HARDENED_BIT, 60 or HARDENED_BIT, HARDENED_BIT, 0, 0)
        val x = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)
        this.credentials = Credentials.create(x)
    }

}