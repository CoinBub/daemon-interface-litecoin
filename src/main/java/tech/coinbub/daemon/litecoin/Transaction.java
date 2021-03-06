package tech.coinbub.daemon.litecoin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.util.List;
import tech.coinbub.daemon.util.YesNoBooleanDeserializer;
import tech.coinbub.daemon.util.YesNoBooleanSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    public String txid;
    public String hash;
    public String blockhash;
    public Long blockindex;
    public Long blocktime;
    public BigDecimal amount;
    public BigDecimal fee;
    public Long confirmations;
    public Boolean trusted;
    public Boolean generated;
    public Long time;
    public Long timereceived;
    public Long locktime;
    public Long version;
    public Long size;
    public Long vsize;
    public List<String> walletconflicts;
    @JsonProperty("bip125-replaceable")
    @JsonDeserialize(using = YesNoBooleanDeserializer.class)
    @JsonSerialize(using = YesNoBooleanSerializer.class)
    public Boolean bip125_replaceable;
    public List<TxInput> vin;
    public List<TxOutput> vout;
    public List<TransactionDetail> details;
    public String comment;
    public String to;
    public String hex;

    public Transaction() {}
    public Transaction(final String txid) {
        this.txid = txid;
    }
}
