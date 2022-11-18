const NFT_Contract = artifacts.require("lemurNFT");

module.exports = function(deployer) {
  deployer.deploy(NFT_Contract);
};