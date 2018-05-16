package tech.coinbub.daemon.litecoin;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TxInput {
    public String coinbase;
    public Long sequence;
}
