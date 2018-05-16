package tech.coinbub.daemon;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.litecoin.Transaction;
import tech.coinbub.daemon.litecoin.TransactionDetail;
import static tech.coinbub.daemon.testutils.BeanMatcher.hasOnly;
import static tech.coinbub.daemon.testutils.BeanPropertyMatcher.property;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class TestGetTransaction {
    @Test
    public void canGetTransactionWithMultipleOuts(final Litecoin litecoin) {
        final Transaction tx = litecoin.gettransaction("76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9");
        assertThat(tx, hasOnly(
                property("txid", is(equalTo("76d3cb8276d4f8376e960278d766ff5066f40a78192a5b161c05739f26182da9"))),
                property("blockhash", is(equalTo("fd5aa78425d7dc2200de2d500d034d544abbccbec50bc3220561a6ede242a97b"))),
                property("blockindex", is(equalTo(0L))),
                property("blocktime", is(equalTo(1526482399L))),
                property("amount", is(equalTo(new BigDecimal("0.0")))),
                property("confirmations", is(equalTo(1L))),
                property("generated", is(equalTo(true))),
                property("time", is(equalTo(1526482387L))),
                property("timereceived", is(equalTo(1526482387L))),
                property("walletconflicts", hasSize(0)),
                property("bip125_replaceable", is(equalTo(false))),
                property("details", hasSize(1)),
                property("hex", is(equalTo("02000000010000000000000000000000000000000000000000000000000000000000000000ffffffff0401780101ffffffff0200f2052a0100000023210206da9d299c43f2e4e51c31ad10cb05946ae60579c342423e8f5ca92501c36068ac0000000000000000266a24aa21a9ede2f61c3f71d1defd3fa999dfa36953755c690689799962b48bebd836974e8cf900000000")))
        ));

        final TransactionDetail detail = tx.details.get(0);
        assertThat(detail, hasOnly(
                property("account", isEmptyString()),
                property("address", is(equalTo("n35eVUVBZZVbhBZeF5x8Q8LRpUonKXdAY7"))),
                property("category", is(equalTo(TransactionDetail.Category.immature))),
                property("amount", is(equalTo(new BigDecimal("50.0")))),
                property("vout", is(equalTo(0L)))
        ));
    }
}
