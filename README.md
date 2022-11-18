<p align="center">
  <h3 align="center">Lemur NFT</h3>

  <p align="center">
    A decentralized application that allows people to mint an NFT.
    <br>
    </p>
</p>

<br>

## Table of contents

- [Table of contents](#table-of-contents)
  - [About the website](#about-the-website)
  - [Technical](#technical)
    - [Technology Used](#technology-used)
    - [Running the project](#running-the-project)
      - [Contracts](#contracts)
      - [Android](#android)
    - [CONTRIBUTING](#contributing)
  - [License](#license)
  - [Versions](#versions)
  - [Contact Information](#contact-information)

### About the website

A decentralized application that allows people to mint an NFT.

<p align="center">
  <img src="./assets/chrome_ODFMadKm4X.gif"/>
</p>

### Technical

#### Technology Used

This project uses: - Web3j - AndroidStudio - VSCode - Truffle - Ngrok - NPM
- Web3j: Lightweight Java and Android library for integration with Ethereum clients. https://www.web3labs.com/web3j-sdk
- Android Studio: Official integrated development environment for Google’s Android operating system. https://developer.android.com/studio
- Truffle: Smart contract development tool. https://trufflesuite.com
- Ngrok: A reverse proxy service for fronting your web service. https://ngrok.com/
- VSCode: A code editor. https://code.visualstudio.com/
- NPM: Node package manager. https://www.npmjs.com/


#### Running the project

##### Contracts

Navigate into the `/contract` folder. Install the npm packages by running. 

```console
npm install
```

Compile the smart contract by running:

```console
truffle compile
```

On a separate terminal, run `ganache`. This runs an ethereum node that we can connect to.

```console
ganache
```

Make sure you save the **seed** and the **first available account**.

Run `ngrok` on a separate terminal. This will act as a forward proxy and expose our ethereum node, making it reachable to the internet.

```console
ngrok http 8545
```

You can now deploy your smart contract to your local ethereum network by running:

```console
truffle migrate --network development
```

Make sure to save the **contract address** since we are going to use it later. 

##### Android

An `apikey.properties` file needs to be created and filled with the following values:

```
    WALLET_ADDRESS="" 
    CONTRACT_ADDRESS="" 
    WEB_SOCKET_URL=""
    SEED=""
    TOKEN_URI="https://gateway.pinata.cloud/ipfs/QmVjYxCFynL9A7V5DRHefLxmsqnNp1eCCv1QLU1cfcGnjz"
```

As a recap
- We obtained the WALLET_ADDRESS by taking the first public key after running the ganache command.
- We obtained the CONTRACT_ADDRESS after running truffle migrate --network development and taking the contract address from the 1_deploy_contract.js section.
- We obtained the SEED after running the ganache command and obtaining the seed generated.

With this, you should be able to run the android app.

The `LemurNft.java` file was created by running:

```console
web3j generate truffle --truffle-json .\build\contracts\lemurNFT.json -o ..\android\app\src\main\java\ -p com.example.lemur
```

The file was then moved to the `android\app\src\main\java\com\example\lemur` directory, next to the `MainActivity.kt` file.

#### CONTRIBUTING

I would/ We'd love to have your help in making **{this app (Lemur NFT)}** better. The project is still very incomplete, but if there's an issue you'd like to see addressed sooner rather than later, let me(/us) know.

Before you contribute though read the contributing guide here: [Contributing.md](https://github.com/peterokwara/lemur-nft/blob/master/CONTRIBUTING.md)

For any concerns, please open an [issue](https://github.com/peterokwara/lemur-nft/issues), or JUST, [fork the project and send a pull request](https://github.com/peterokwara/lemur-nft/pulls).

<hr>

### License

- see [LICENSE](https://github.com/peterokwara/lemur-nft/blob/master/LICENSE) file

### Versions

- Version 1.0 DATE 10/04/2022

### Contact Information

If you have found any bugs, or have any feedback or questions and or want to post a feature request please use the [Issuetracker](https://github.com/peterokwara/StarNotary2.0/issues) to report them.

<hr>

[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source-200x33.png?v=103)](#)

<br>

[![license](https://img.shields.io/github/license/mashape/apistatus.svg?style=for-the-badge)](https://github.com/peterokwara/lemur-nft/blob/master/LICENSE)







