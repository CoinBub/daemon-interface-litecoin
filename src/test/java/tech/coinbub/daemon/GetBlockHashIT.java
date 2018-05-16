package tech.coinbub.daemon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class GetBlockHashIT {
    public static final Long HEIGHT = 22L;

    @Test
    public void canGetBlockHash(final Litecoin litecoin) {
        final String best = litecoin.getblockhash(HEIGHT);
        assertThat(best, is(equalTo("fd589520e81b383f7113029e576c3697ee91a983184cc6601bb7cfb055d35c8e")));
    }
}
