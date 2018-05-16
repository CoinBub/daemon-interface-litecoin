package tech.coinbub.daemon;

import java.math.BigDecimal;
import tech.coinbub.daemon.litecoin.Block;
import tech.coinbub.daemon.litecoin.Transaction;

/**
 * `litecoin-cli help` yields:
 * == Blockchain ==
 * getbestblockhash
 * getblock "blockhash" ( verbosity ) 
 * getblockchaininfo
 * getblockcount
 * getblockhash height
 * getblockheader "hash" ( verbose )
 * getchaintips
 * getchaintxstats ( nblocks blockhash )
 * getdifficulty
 * getmempoolancestors txid (verbose)
 * getmempooldescendants txid (verbose)
 * getmempoolentry txid
 * getmempoolinfo
 * getrawmempool ( verbose )
 * gettxout "txid" n ( include_mempool )
 * gettxoutproof ["txid",...] ( blockhash )
 * gettxoutsetinfo
 * preciousblock "blockhash"
 * pruneblockchain
 * verifychain ( checklevel nblocks )
 * verifytxoutproof "proof"
 * 
 * == Control ==
 * getinfo
 * getmemoryinfo ("mode")
 * help ( "command" )
 * stop
 * uptime
 * 
 * == Generating ==
 * generate nblocks ( maxtries )
 * generatetoaddress nblocks address (maxtries)
 * 
 * == Mining ==
 * getblocktemplate ( TemplateRequest )
 * getmininginfo
 * getnetworkhashps ( nblocks height )
 * prioritisetransaction <txid> <dummy value> <fee delta>
 * submitblock "hexdata"  ( "dummy" )
 * 
 * == Network ==
 * addnode "node" "add|remove|onetry"
 * clearbanned
 * disconnectnode "[address]" [nodeid]
 * getaddednodeinfo ( "node" )
 * getconnectioncount
 * getnettotals
 * getnetworkinfo
 * getpeerinfo
 * listbanned
 * ping
 * setban "subnet" "add|remove" (bantime) (absolute)
 * setnetworkactive true|false
 * 
 * == Rawtransactions ==
 * combinerawtransaction ["hexstring",...]
 * createrawtransaction [{"txid":"id","vout":n},...] {"address":amount,"data":"hex",...} ( locktime ) ( replaceable )
 * decoderawtransaction "hexstring"
 * decodescript "hexstring"
 * fundrawtransaction "hexstring" ( options )
 * getrawtransaction "txid" ( verbose )
 * sendrawtransaction "hexstring" ( allowhighfees )
 * signrawtransaction "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...] ["privatekey1",...] sighashtype )
 * 
 * == Util ==
 * createmultisig nrequired ["key",...]
 * estimatefee nblocks
 * estimatesmartfee conf_target ("estimate_mode")
 * signmessagewithprivkey "privkey" "message"
 * validateaddress "address"
 * verifymessage "address" "signature" "message"
 * 
 * == Wallet ==
 * abandontransaction "txid"
 * abortrescan
 * addmultisigaddress nrequired ["key",...] ( "account" )
 * addwitnessaddress "address"
 * backupwallet "destination"
 * bumpfee "txid" ( options ) 
 * dumpprivkey "address"
 * dumpwallet "filename"
 * encryptwallet "passphrase"
 * getaccount "address"
 * getaccountaddress "account"
 * getaddressesbyaccount "account"
 * getbalance ( "account" minconf include_watchonly )
 * getnewaddress ( "account" )
 * getrawchangeaddress
 * getreceivedbyaccount "account" ( minconf )
 * getreceivedbyaddress "address" ( minconf )
 * gettransaction "txid" ( include_watchonly )
 * getunconfirmedbalance
 * getwalletinfo
 * importaddress "address" ( "label" rescan p2sh )
 * importmulti "requests" ( "options" )
 * importprivkey "privkey" ( "label" ) ( rescan )
 * importprunedfunds
 * importpubkey "pubkey" ( "label" rescan )
 * importwallet "filename"
 * keypoolrefill ( newsize )
 * listaccounts ( minconf include_watchonly)
 * listaddressgroupings
 * listlockunspent
 * listreceivedbyaccount ( minconf include_empty include_watchonly)
 * listreceivedbyaddress ( minconf include_empty include_watchonly)
 * listsinceblock ( "blockhash" target_confirmations include_watchonly include_removed )
 * listtransactions ( "account" count skip include_watchonly)
 * listunspent ( minconf maxconf  ["addresses",...] [include_unsafe] [query_options])
 * listwallets
 * lockunspent unlock ([{"txid":"txid","vout":n},...])
 * move "fromaccount" "toaccount" amount ( minconf "comment" )
 * removeprunedfunds "txid"
 * sendfrom "fromaccount" "toaddress" amount ( minconf "comment" "comment_to" )
 * sendmany "fromaccount" {"address":amount,...} ( minconf "comment" ["address",...] replaceable conf_target "estimate_mode")
 * sendtoaddress "address" amount ( "comment" "comment_to" subtractfeefromamount replaceable conf_target "estimate_mode")
 * setaccount "address" "account"
 * settxfee amount
 * signmessage "address" "message"
 */
public interface Litecoin {
    /**
     * `getnewaddress ( "account" )`
     * 
     * Returns a new Litecoin address for receiving payments.
     * If 'account' is specified (DEPRECATED), it is added to the address book 
     * so payments received with the address will be credited to 'account'.
     * 
     * Arguments:
     * 1. "account"        (string, optional) DEPRECATED. The account name for the address to be linked to. If not provided, the default account "" is used. It can also be set to the empty string "" to represent the default account. The account does not need to exist, it will be created if there is no account by the given name.
     * 
     * Result:
     * "address"    (string) The new litecoin address
     * 
     * Examples:
     * > litecoin-cli getnewaddress 
     * > curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getnewaddress", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:9332/
     */
    String getnewaddress();
    String getnewaddress(String account);

    /**
     * `getbestblockhash`
     * 
     * Returns the hash of the best (tip) block in the longest blockchain.
     * 
     * Result:
     * "hex"      (string) the block hash hex encoded
     * 
     * Examples:
     * > litecoin-cli getbestblockhash 
     * > curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getbestblockhash", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:9332/
     * 
     */
    String getbestblockhash();

    /**
     * `getblock "blockhash" ( verbosity )`
     * 
     * If verbosity is 0, returns a string that is serialized, hex-encoded data for block 'hash'.
     * If verbosity is 1, returns an Object with information about block <hash>.
     * If verbosity is 2, returns an Object with information about block <hash> and information about each transaction. 
     * 
     * Arguments:
     * 1. "blockhash"          (string, required) The block hash
     * 2. verbosity              (numeric, optional, default=1) 0 for hex encoded data, 1 for a json object, and 2 for json object with transaction data
     * 
     * Result (for verbosity = 0):
     * "data"             (string) A string that is serialized, hex-encoded data for block 'hash'.
     * 
     * Result (for verbosity = 1):
     * {
     *   "hash" : "hash",     (string) the block hash (same as provided)
     *   "confirmations" : n,   (numeric) The number of confirmations, or -1 if the block is not on the main chain
     *   "size" : n,            (numeric) The block size
     *   "strippedsize" : n,    (numeric) The block size excluding witness data
     *   "weight" : n           (numeric) The block weight as defined in BIP 141
     *   "height" : n,          (numeric) The block height or index
     *   "version" : n,         (numeric) The block version
     *   "versionHex" : "00000000", (string) The block version formatted in hexadecimal
     *   "merkleroot" : "xxxx", (string) The merkle root
     *   "tx" : [               (array of string) The transaction ids
     *      "transactionid"     (string) The transaction id
     *      ,...
     *   ],
     *   "time" : ttt,          (numeric) The block time in seconds since epoch (Jan 1 1970 GMT)
     *   "mediantime" : ttt,    (numeric) The median block time in seconds since epoch (Jan 1 1970 GMT)
     *   "nonce" : n,           (numeric) The nonce
     *   "bits" : "1d00ffff", (string) The bits
     *   "difficulty" : x.xxx,  (numeric) The difficulty
     *   "chainwork" : "xxxx",  (string) Expected number of hashes required to produce the chain up to this block (in hex)
     *   "previousblockhash" : "hash",  (string) The hash of the previous block
     *   "nextblockhash" : "hash"       (string) The hash of the next block
     * }
     * 
     * Result (for verbosity = 2):
     * {
     *   ...,                     Same output as verbosity = 1.
     *   "tx" : [               (array of Objects) The transactions in the format of the getrawtransaction RPC. Different from verbosity = 1 "tx" result.
     *          ,...
     *   ],
     *   ,...                     Same output as verbosity = 1.
     * }
     * 
     * Examples:
     * > litecoin-cli getblock "e2acdf2dd19a702e5d12a925f1e984b01e47a933562ca893656d4afb38b44ee3"
     * > curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblock", "params": ["e2acdf2dd19a702e5d12a925f1e984b01e47a933562ca893656d4afb38b44ee3"] }' -H 'content-type: text/plain;' http://127.0.0.1:9332/
     */
    Block getblock(String hash);
    Block getblock(String hash, int verbosity);
    
    /**
     * `gettransaction "txid" ( include_watchonly )`
     * 
     * Get detailed information about in-wallet transaction <txid>
     * 
     * Arguments:
     * 1. "txid"                  (string, required) The transaction id
     * 2. "include_watchonly"     (bool, optional, default=false) Whether to include watch-only addresses in balance calculation and details[]
     * 
     * Result:
     * {
     *   "amount" : x.xxx,        (numeric) The transaction amount in LTC
     *   "fee": x.xxx,            (numeric) The amount of the fee in LTC. This is negative and only available for the 
     *                               'send' category of transactions.
     *   "confirmations" : n,     (numeric) The number of confirmations
     *   "blockhash" : "hash",  (string) The block hash
     *   "blockindex" : xx,       (numeric) The index of the transaction in the block that includes it
     *   "blocktime" : ttt,       (numeric) The time in seconds since epoch (1 Jan 1970 GMT)
     *   "txid" : "transactionid",   (string) The transaction id.
     *   "time" : ttt,            (numeric) The transaction time in seconds since epoch (1 Jan 1970 GMT)
     *   "timereceived" : ttt,    (numeric) The time received in seconds since epoch (1 Jan 1970 GMT)
     *   "bip125-replaceable": "yes|no|unknown",  (string) Whether this transaction could be replaced due to BIP125 (replace-by-fee);
     *                                                    may be unknown for unconfirmed transactions not in the mempool
     *   "details" : [
     *     {
     *       "account" : "accountname",      (string) DEPRECATED. The account name involved in the transaction, can be "" for the default account.
     *       "address" : "address",          (string) The litecoin address involved in the transaction
     *       "category" : "send|receive",    (string) The category, either 'send' or 'receive'
     *       "amount" : x.xxx,                 (numeric) The amount in LTC
     *       "label" : "label",              (string) A comment for the address/transaction, if any
     *       "vout" : n,                       (numeric) the vout value
     *       "fee": x.xxx,                     (numeric) The amount of the fee in LTC. This is negative and only available for the 
     *                                            'send' category of transactions.
     *       "abandoned": xxx                  (bool) 'true' if the transaction has been abandoned (inputs are respendable). Only available for the 
     *                                            'send' category of transactions.
     *     }
     *     ,...
     *   ],
     *   "hex" : "data"         (string) Raw data for transaction
     * }
     * 
     * Examples:
     * > litecoin-cli gettransaction "c1700d6dd3e690866de56686e893cbe4e637eb5d84e3591cdfbbdbb0fcee49f8"
     * > litecoin-cli gettransaction "c1700d6dd3e690866de56686e893cbe4e637eb5d84e3591cdfbbdbb0fcee49f8" true
     * > curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "gettransaction", "params": ["c1700d6dd3e690866de56686e893cbe4e637eb5d84e3591cdfbbdbb0fcee49f8"] }' -H 'content-type: text/plain;' http://127.0.0.1:9332/
     */
    Transaction gettransaction(String txid);
    Transaction gettransaction(String txid, boolean include_watchonly);

    /**
     * sendtoaddress "address" amount ( "comment" "comment_to" subtractfeefromamount replaceable conf_target "estimate_mode")
     * 
     * Send an amount to a given address.
     * 
     * Arguments:
     * 1. "address"            (string, required) The litecoin address to send to.
     * 2. "amount"             (numeric or string, required) The amount in LTC to send. eg 0.1
     * 3. "comment"            (string, optional) A comment used to store what the transaction is for. 
     *                              This is not part of the transaction, just kept in your wallet.
     * 4. "comment_to"         (string, optional) A comment to store the name of the person or organization 
     *                              to which you're sending the transaction. This is not part of the 
     *                              transaction, just kept in your wallet.
     * 5. subtractfeefromamount  (boolean, optional, default=false) The fee will be deducted from the amount being sent.
     *                              The recipient will receive less litecoins than you enter in the amount field.
     * 6. replaceable            (boolean, optional) Allow this transaction to be replaced by a transaction with higher fees via BIP 125
     * 7. conf_target            (numeric, optional) Confirmation target (in blocks)
     * 8. "estimate_mode"      (string, optional, default=UNSET) The fee estimate mode, must be one of:
     *        "UNSET"
     *        "ECONOMICAL"
     *        "CONSERVATIVE"
     * 
     * Result:
     * "txid"                  (string) The transaction id.
     * 
     * Examples:
     * > litecoin-cli sendtoaddress "LEr4HnaefWYHbMGXcFp2Po1NPRUeIk8km2" 0.1
     * > litecoin-cli sendtoaddress "LEr4HnaefWYHbMGXcFp2Po1NPRUeIk8km2" 0.1 "donation" "seans outpost"
     * > litecoin-cli sendtoaddress "LEr4HnaefWYHbMGXcFp2Po1NPRUeIk8km2" 0.1 "" "" true
     * > curl --user myusername --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "sendtoaddress", "params": ["LEr4HnaefWYHbMGXcFp2Po1NPRUeIk8km2", 0.1, "donation", "seans outpost"] }' -H 'content-type: text/plain;' http://127.0.0.1:9332/
     */
    String sendtoaddress(String address, BigDecimal amount);
    String sendtoaddress(String address, BigDecimal amount, String comment);
    String sendtoaddress(String address, BigDecimal amount, String comment, String comment_to);
    String sendtoaddress(String address, BigDecimal amount, String comment, String comment_to, boolean subtractfeefromamount);
    String sendtoaddress(String address, BigDecimal amount, String comment, String comment_to, boolean subtractfeefromamount, int conf_target);
    String sendtoaddress(String address, BigDecimal amount, String comment, String comment_to, boolean subtractfeefromamount, int conf_target, EstimateMode estimate_mode);

    public enum EstimateMode {
        UNSET,
        ECONOMICAL,
        CONSERVATIVE
    }
}
