package org.galilelj.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hash Engineering Solutions
 * Date: 5/3/14
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {
    public static final String coinName = "Galilel";
    public static final String coinTicker = "GALI";
    public static final String coinURIScheme = "galilel";
    public static final String cryptsyMarketId = "155";
    public static final String cryptsyMarketCurrency = "GALI";
    public static final String PATTERN_PRIVATE_KEY_START_UNCOMPRESSED = "[7]";
    public static final String PATTERN_PRIVATE_KEY_START_COMPRESSED = "[X]";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;

    public static final String UNSPENT_API_URL = "https://chainz.cryptoid.info/gali/api.dws?q=unspent";
    public enum UnspentAPIType {
        BitEasy,
        Blockr,
        Abe,
        Cryptoid,
    };
    public static final UnspentAPIType UnspentAPI = UnspentAPIType.Cryptoid;

    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://explorer.galilel.cloud/"; // blockr.io
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";                         // blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";                          // blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                             // blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "https://explorer.testnet.galilel.cloud/";

    public static final String DONATION_ADDRESS = "UUr5nDmykhun1HWM7mJAqLVeLzoGtx19dX";         // Galilel donation address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };
    public static final CoinHash coinPOWHash = CoinHash.x11;

    public static boolean checkpointFileSupport = true;

    public static final int TARGET_TIMESPAN = (int)(1 * 60);             // 1 minute per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 60);              // 1 minute seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING; // 1 block

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN; // 1 minute
    }

    public static int spendableCoinbaseDepth = 99;             // main.h: static const int COINBASE_MATURITY
    public static final long MAX_COINS = 25000000;             // main.h: MAX_MONEY
    public static final long DEFAULT_MIN_TX_FEE = 10000;       // main.cpp: CFeeRate minRelayTxFee = CFeeRate(10000);
    public static final long DUST_LIMIT = 30000;               // main.h CTransaction::GetMinFee 0.01 coins
    public static final long INSTANTX_FEE = 100000;            // 0.001 GALI
    public static final boolean feeCanBeRaised = false;

    // Galilel 3.2.0
    public static final int PROTOCOL_VERSION = 70721;          // version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 70721;      // version.h MIN_PROTO_VERSION
    public static final int MAX_BLOCK_SIZE = 2 * 1000 * 1000;  // block.h MAX_BLOCK_SIZE_CURRENT
    public static final boolean supportsBloomFiltering = true; // Requires PROTOCOL_VERSION 70000 in the client
    public static final int Port = 36001;                      // protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 56123;                  // protocol.h GetDefaultPort(testnet=true)

    // LibZerocoin starting block height
    public static final long MAINNET_ZEROCOIN_STARTING_BLOCK_HEIGHT = 245000;
    public static final long TESTNET_ZEROCOIN_STARTING_BLOCK_HEIGHT = 250;

    // Production
    public static final int AddressHeader = 68;                // base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 16;                   // base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final int dumpedPrivateKeyHeader = 128;      // common to all coins
    public static final long PacketMagic = 0xd2c31554;         // 0xd2, 0xc3, 0x15, 0x54

    // Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = 0x1e0ffff0;                                                // main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1527217408L;                                                           // main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = 121699L;                                                              // main.cpp: LoadBlockIndex
    static public String genesisHash = "00000a15f1dd0b452c85b89d7e8a2968205e19550b1c2f12909367a04afc2855";       // main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "1dd81cdda448e9346a94f82e0d6c9fa7a876b59124da4dc49b66531d3ef11822";
    static public int genesisBlockValue = 0;                                                                     // chainparams.cpp: txNew.vout[0].nValue

    // main.cpp GetBlockValue(height, fee)
    public static final Coin GetBlockReward(int height)
    {
        Coin nSubsidy = Coin.valueOf(5, 0);

        /* block rewards. */
        if (height > 430000) {
            nSubsidy = Coin.valueOf(5, 0);
        } else if (height > 340000) {
            nSubsidy = Coin.valueOf(10, 0);
        } else if (height > 250000) {
            nSubsidy = Coin.valueOf(13, 5);
        } else if (height > 205000) {
            nSubsidy = Coin.valueOf(25, 0);
        } else if (height > 160000) {
            nSubsidy = Coin.valueOf(50, 0);
        } else if (height > 100000) {
            nSubsidy = Coin.valueOf(60, 0);
        } else if (height > 42000) {
            nSubsidy = Coin.valueOf(70, 0);
        } else if (height > 22000) {
            nSubsidy = Coin.valueOf(80, 0);
        } else if (height > 12000) {
            nSubsidy = Coin.valueOf(90, 0);
        } else if (height > 1500) {
            nSubsidy = Coin.valueOf(100, 0);
        } else if (height > 1) {
            nSubsidy = Coin.valueOf(1, 0);
        } else if (height == 1) {
            nSubsidy = Coin.valueOf(220000, 0);
        }

        return nSubsidy;
    }

    // taken from the raw data of the block explorer
    static public String genesisTxInBytes = "04ffff001d01043747616c696c656c20436f696e2053746172742040204672696461792c2032352e204d617920323031382030343a30303a303020474d542e";
    public static final String genesisTxPubKey = "87268afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f";

    // net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
        "seed1.galilel.cloud",
        "seed2.galilel.cloud",
        "seed3.galilel.cloud",
        "seed4.galilel.cloud",
        "seed5.galilel.cloud",
        "seed6.galilel.cloud",
        "seed7.galilel.cloud",
        "seed8.galilel.cloud"
    };
    public static int minBroadcastConnections = 3; // 0 for default; we need more peers.

    // Testnet
    public static final boolean supportsTestNet = true;
    public static final int testnetAddressHeader = 83;                   // base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 18;                      // base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x14645465;            // 0x14, 0x64, 0x54, 0x65
    public static final String testnetGenesisHash =  "000008e4c24baa9a3503e6dc2f3b459843441a0d56677b1e4bd0b9a381ca987f";
    static public long testnetGenesisBlockDifficultyTarget = 0x1e0ffff0; // main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1540587600L;            // main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = 1745260L;              // main.cpp: LoadBlockIndex

    // main.cpp GetBlockValue(height, fee)
    public static final Coin testnetGetBlockReward(int height)
    {
        Coin nSubsidy = Coin.valueOf(5, 0);

        /* block rewards. */
        if (height > 430000) {
            nSubsidy = Coin.valueOf(5, 0);
        } else if (height > 340000) {
            nSubsidy = Coin.valueOf(10, 0);
        } else if (height > 250000) {
            nSubsidy = Coin.valueOf(13, 5);
        } else if (height > 205000) {
            nSubsidy = Coin.valueOf(25, 0);
        } else if (height > 160000) {
            nSubsidy = Coin.valueOf(50, 0);
        } else if (height > 100000) {
            nSubsidy = Coin.valueOf(60, 0);
        } else if (height > 42000) {
            nSubsidy = Coin.valueOf(70, 0);
        } else if (height > 22000) {
            nSubsidy = Coin.valueOf(80, 0);
        } else if (height > 12000) {
            nSubsidy = Coin.valueOf(90, 0);
        } else if (height > 200) {
            nSubsidy = Coin.valueOf(100, 0);
        } else if (height > 1) {
            nSubsidy = Coin.valueOf(1, 0);
        } else if (height == 1) {
            nSubsidy = Coin.valueOf(220000, 0);
        }

        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 210000;                             // chainparams.cpp: nSubsidyHalvingInterval
    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL); // main.cpp: bnProofOfWorkLimit (~uint256(0) >> 20); // GALI starting difficulty is 1 / 2^12

    // net.cpp strDNSSeed
    static public String[] testnetDnsSeeds = new String[] {
        "seed1.testnet.galilel.cloud",
        "seed2.testnet.galilel.cloud",
        "seed3.testnet.galilel.cloud",
        "seed4.testnet.galilel.cloud",
        "seed5.testnet.galilel.cloud",
        "seed6.testnet.galilel.cloud",
        "seed7.testnet.galilel.cloud",
        "seed8.testnet.galilel.cloud"
    };

    // from chainparams.cpp: vAlertPubKey
    public static final String SATOSHI_KEY = "98306db20be5c53b93678e2e41c9def7af38197280c65e813f682adf2ed501ac186022562dbdf2ce3204d07432660fb61ecad8e78b6b8d39c568fb892db8ecb736";
    public static final String TESTNET_SATOSHI_KEY = "04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f";

    // The string returned by getId() for the main, production network where people trade things.
    public static final String ID_MAINNET = "org.galilel.production";

    // The string returned by getId() for the testnet.
    public static final String ID_TESTNET = "org.galilel.test";

    // Unit test network.
    public static final String ID_UNITTESTNET = "com.google.galilel.unittest";

    // checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
//        checkpoints.put(  1500, Sha256Hash.wrap("000000aaf0300f59f49bc3e970bad15c11f961fe2347accffff19d96ec9778e3"));
//        checkpoints.put(  4991, Sha256Hash.wrap("000000003b01809551952460744d5dbb8fcbd6cbae3c220267bf7fa43f837367"));
//        checkpoints.put(  9918, Sha256Hash.wrap("00000000213e229f332c0ffbe34defdaa9e74de87f2d8d1f01af8d121c3c170b"));
//        checkpoints.put( 16912, Sha256Hash.wrap("00000000075c0d10371d55a60634da70f197548dbbfa4123e12abfcbc5738af9"));
//        checkpoints.put( 23912, Sha256Hash.wrap("0000000000335eac6703f3b1732ec8b2f89c3ba3a7889e5767b090556bb9a276"));
//        checkpoints.put( 35457, Sha256Hash.wrap("0000000000b0ae211be59b048df14820475ad0dd53b9ff83b010f71a77342d9f"));
//        checkpoints.put( 45479, Sha256Hash.wrap("000000000063d411655d590590e16960f15ceea4257122ac430c6fbe39fbf02d"));
//        checkpoints.put( 55895, Sha256Hash.wrap("0000000000ae4c53a43639a4ca027282f69da9c67ba951768a20415b6439a2d7"));
//        checkpoints.put( 68899, Sha256Hash.wrap("0000000000194ab4d3d9eeb1f2f792f21bb39ff767cb547fe977640f969d77b7"));
//        checkpoints.put( 74619, Sha256Hash.wrap("000000000011d28f38f05d01650a502cc3f4d0e793fbc26e2a2ca71f07dc3842"));
//        checkpoints.put( 75095, Sha256Hash.wrap("0000000000193d12f6ad352a9996ee58ef8bdc4946818a5fec5ce99c11b87f0d"));
//        checkpoints.put( 88805, Sha256Hash.wrap("00000000001392f1652e9bf45cd8bc79dc60fe935277cd11538565b4a94fa85f"));
//        checkpoints.put( 90544, Sha256Hash.wrap("000000000001b284b79a44a95215d7e6cf9e22cd4f9b562f2cc796e941e0e411"));
    }

    // Unit Test Information
    public static final String UNITTEST_ADDRESS = "ac2BY4nxixXgFZnR1zoV6EyBNPgpGT6pGS";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "VZXRkbxYDgVVQvGiR4wDyHpqn6TW7zEcSJ2uu5E1thTfhyWpebS6";
}
