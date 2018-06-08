package tech.coinbub.daemon;

import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static tech.coinbub.daemon.GetBlockHashIT.HEIGHT;
import tech.coinbub.daemon.normalization.model.Block;
import tech.coinbub.daemon.normalization.model.Transaction;
import tech.coinbub.daemon.normalization.model.TransactionDetail;
import static tech.coinbub.daemon.testutils.BeanMatcher.hasOnly;
import static tech.coinbub.daemon.testutils.BeanPropertyMatcher.property;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class NormalizedLitecoinIT {
    @Test
    public void testGetblockhash(final NormalizedLitecoin normalized) {
        assertThat(normalized.getblockhash(HEIGHT), is(equalTo("fd589520e81b383f7113029e576c3697ee91a983184cc6601bb7cfb055d35c8e")));
    }

    @Test
    public void testGetblock(final NormalizedLitecoin normalized) {
        final Block block = normalized.getblock("fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b");
        assertThat(block, hasOnly(
                property("hash", is(equalTo("fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b"))),
                property("confirmations", is(equalTo(1L))),
                property("size", is(equalTo(227L))),
                property("height", is(equalTo(120L))),
                property("tx", hasSize(1)),
                property("time", is(equalTo(1526482399L))),
                property("previousblockhash", is(equalTo("93b514a0bae42cdb3e92dca82ca7bd93644478fc980914a486217271a7c241d3")))
        ));
        
        assertThat(block.tx.get(0), is(equalTo("76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9")));
    }

    @Test
    public void testGettransaction(final NormalizedLitecoin normalized) {
        final Transaction tx = normalized.gettransaction("76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9");
        assertThat(tx, hasOnly(
                property("id", not(isEmptyString())),
                property("blockhash", is(equalTo("fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b"))),
                property("amount", is(equalTo(new BigDecimal("0.0")))),
                property("confirmations", is(equalTo(1L))),
                property("time", is(equalTo(1526482387L))),
                property("details", hasSize(1))
        ));

        final TransactionDetail detail = tx.details.get(0);
        assertThat(detail, hasOnly(
                property("address", is(equalTo("n35eVUVBZZVbhBZeF5x8Q8LRpUonKXdAY7"))),
                property("amount", is(equalTo(new BigDecimal("50.0"))))
        ));
    }

    @Test
    public void testGetnewaddress(final NormalizedLitecoin normalized) {
        assertThat(normalized.getnewaddress().length(), is(equalTo(34)));
    }

    @Test
    public void testSendToAddressNoComments(final NormalizedLitecoin normalized) {
        final String txid = normalized.sendtoaddress(SendToAddressIT.VALID_ADDRESS, BigDecimal.ONE);
        final Transaction tx = normalized.gettransaction(txid);
        assertThat(tx, hasOnly(
                property("id", not(isEmptyString())),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001")))),
                property("confirmations", is(equalTo(0L))),
                property("time", is(not(nullValue()))),
                property("details", hasSize(1))
        ));

        assertThat(tx.details.get(0), hasOnly(
                property("address", is(equalTo(SendToAddressIT.VALID_ADDRESS))),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001"))))
        ));
    }

    @Test
    public void testSendToAddressSourceComment(final NormalizedLitecoin normalized) {
        final String txid = normalized.sendtoaddress(SendToAddressIT.VALID_ADDRESS, BigDecimal.ONE, "test transaction!");
        final Transaction tx = normalized.gettransaction(txid);
        assertThat(tx, hasOnly(
                property("id", not(isEmptyString())),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001")))),
                property("confirmations", is(equalTo(0L))),
                property("time", is(not(nullValue()))),
                property("comment_from", is(equalTo("test transaction!"))),
                property("details", hasSize(1))
        ));

        assertThat(tx.details.get(0), hasOnly(
                property("address", is(equalTo(SendToAddressIT.VALID_ADDRESS))),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001"))))
        ));
    }

    @Test
    public void testSendToAddressDestComment(final NormalizedLitecoin normalized) {
        final String txid = normalized.sendtoaddress(SendToAddressIT.VALID_ADDRESS, BigDecimal.ONE, "test transaction!", "receiving test!");
        final Transaction tx = normalized.gettransaction(txid);
        assertThat(tx, hasOnly(
                property("id", not(isEmptyString())),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001")))),
                property("confirmations", is(equalTo(0L))),
                property("time", is(not(nullValue()))),
                property("comment_from", is(equalTo("test transaction!"))),
                property("comment_to", is(equalTo("receiving test!"))),
                property("details", hasSize(1))
        ));

        assertThat(tx.details.get(0), hasOnly(
                property("address", is(equalTo(SendToAddressIT.VALID_ADDRESS))),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(closeTo(new BigDecimal("-0.00384"), new BigDecimal("0.00001"))))
        ));
    }

}
