package tech.coinbub.daemon.litecoin;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Block {
    public String hash;
    public Long confirmations;
    public Long strippedsize;
    public Long size;
    public Long weight;
    public Long height;
    public Long version;
    public String versionHex;
    public String merkleroot;
    public List<Transaction> tx;
    public Long time;
    public Long mediantime;
    public Long nonce;
    public String bits;
    public BigDecimal difficulty;
    public String chainwork;
    public String previousblockhash;
}

//root@test:~$ litecoin-cli getblock fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b
//{
//  "hash": "fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b",
//  "confirmations": 1,
//  "strippedsize": 227,
//  "size": 227,
//  "weight": 908,
//  "height": 120,
//  "version": 536870912,
//  "versionHex": "20000000",
//  "merkleroot": "76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9",
//  "tx": [
//    "76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9"
//  ],
//  "time": 1526482399,
//  "mediantime": 1526482398,
//  "nonce": 0,
//  "bits": "207fffff",
//  "difficulty": 4.656542373906925e-10,
//  "chainwork": "00000000000000000000000000000000000000000000000000000000000000f2",
//  "previousblockhash": "93b514a0bae42cdb3e92dca82ca7bd93644478fc980914a486217271a7c241d3"
//}
//root@test:~$ litecoin-cli getblock fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b 2
//{
//  "hash": "fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b",
//  "confirmations": 1,
//  "strippedsize": 227,
//  "size": 227,
//  "weight": 908,
//  "height": 120,
//  "version": 536870912,
//  "versionHex": "20000000",
//  "merkleroot": "76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9",
//  "tx": [
//    {
//      "txid": "76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9",
//      "hash": "76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9",
//      "version": 2,
//      "size": 146,
//      "vsize": 146,
//      "locktime": 0,
//      "vin": [
//        {
//          "coinbase": "01780101",
//          "sequence": 4294967295
//        }
//      ],
//      "vout": [
//        {
//          "value": 50.00000000,
//          "n": 0,
//          "scriptPubKey": {
//            "asm": "0206da9d299c43f2e4e51c31ad10cb05946ae60579c342423e8f5ca92501c36068 OP_CHECKSIG",
//            "hex": "210206da9d299c43f2e4e51c31ad10cb05946ae60579c342423e8f5ca92501c36068ac",
//            "reqSigs": 1,
//            "type": "pubkey",
//            "addresses": [
//              "n35eVUVBZZVbhBZeF5x8Q8LRpUonKXdAY7"
//            ]
//          }
//        }, 
//        {
//          "value": 0.00000000,
//          "n": 1,
//          "scriptPubKey": {
//            "asm": "OP_RETURN aa21a9ede2f61c3f71d1defd3fa999dfa36953755c690689799962b48bebd836974e8cf9",
//            "hex": "6a24aa21a9ede2f61c3f71d1defd3fa999dfa36953755c690689799962b48bebd836974e8cf9",
//            "type": "nulldata"
//          }
//        }
//      ],
//      "hex": "02000000010000000000000000000000000000000000000000000000000000000000000000ffffffff0401780101ffffffff0200f2052a0100000023210206da9d299c43f2e4e51c31ad10cb05946ae60579c342423e8f5ca92501c36068ac0000000000000000266a24aa21a9ede2f61c3f71d1defd3fa999dfa36953755c690689799962b48bebd836974e8cf900000000"
//    }
//  ],
//  "time": 1526482399,
//  "mediantime": 1526482398,
//  "nonce": 0,
//  "bits": "207fffff",
//  "difficulty": 4.656542373906925e-10,
//  "chainwork": "00000000000000000000000000000000000000000000000000000000000000f2",
//  "previousblockhash": "93b514a0bae42cdb3e92dca82ca7bd93644478fc980914a486217271a7c241d3"
//}
