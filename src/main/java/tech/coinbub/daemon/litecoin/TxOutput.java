package tech.coinbub.daemon.litecoin;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TxOutput {
    public BigDecimal value;
    public Long n;
    public ScriptPublicKey scriptPubKey;
}
