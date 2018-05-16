package tech.coinbub.daemon.litecoin;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptPublicKey {
    public String asm;
    public String hex;
    public Long reqSigs;
    public Type type;
    public List<String> addresses;

    public enum Type {
        pubkey,
        nulldata
    }
}
