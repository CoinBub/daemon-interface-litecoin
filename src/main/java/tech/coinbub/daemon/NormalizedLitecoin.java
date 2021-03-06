package tech.coinbub.daemon;

import tech.coinbub.daemon.normalization.Normalized;
import java.math.BigDecimal;
import java.util.ArrayList;
import tech.coinbub.daemon.Litecoin;
import tech.coinbub.daemon.litecoin.Block;
import tech.coinbub.daemon.litecoin.Transaction;
import tech.coinbub.daemon.litecoin.TransactionDetail;

public class NormalizedLitecoin implements Normalized<Litecoin> {
    private final Litecoin client;

    public NormalizedLitecoin(final Litecoin client) {
        this.client = client;
    }

    @Override
    public String getblockhash(final Long height) {
        return client.getblockhash(height);
    }

    @Override
    public tech.coinbub.daemon.normalization.model.Block getblock(final String hash) {
        final Block block = client.getblock(hash);
        final tech.coinbub.daemon.normalization.model.Block result
                = new tech.coinbub.daemon.normalization.model.Block();
        result.hash = block.hash;
        result.confirmations = block.confirmations;
        result.size = block.size;
        result.height = block.height;
        result.time = block.time;
        result.previousblockhash = block.previousblockhash;
        
        if (!block.tx.isEmpty()) {
            result.tx = new ArrayList<>();
        }
        for (Transaction tx : block.tx) {
            result.tx.add(tx.txid);
        }
        return result;
    }

    @Override
    public tech.coinbub.daemon.normalization.model.Transaction gettransaction(final String txid) {
        return normalizeTransaction(client.gettransaction(txid));
    }

    @Override
    public String getnewaddress() {
        return client.getnewaddress();
    }

    @Override
    public String sendtoaddress(final String address, final BigDecimal amount) {
        return client.sendtoaddress(address, amount);
    }

    @Override
    public String sendtoaddress(final String address, final BigDecimal amount, final String comment_from) {
        return client.sendtoaddress(address, amount, comment_from);
    }

    @Override
    public String sendtoaddress(final String address, final BigDecimal amount, final String comment_from, final String comment_to) {
        return client.sendtoaddress(address, amount, comment_from, comment_to);
    }

    //
    // Helpers and Utilities
    //
    private tech.coinbub.daemon.normalization.model.Transaction normalizeTransaction(final Transaction tx) {
        final tech.coinbub.daemon.normalization.model.Transaction result
                = new tech.coinbub.daemon.normalization.model.Transaction();
        result.id = tx.txid;
        result.amount = tx.amount;
        result.fee = tx.fee;
        result.time = tx.time;
        result.size = tx.size;
        result.confirmations = tx.confirmations;
        result.blockhash = tx.blockhash;
        result.comment_from = tx.comment;
        result.comment_to = tx.to;

        if (!tx.details.isEmpty()) {
            result.details = new ArrayList<>();
        }
        for (TransactionDetail rawDetail : tx.details) {
            final tech.coinbub.daemon.normalization.model.TransactionDetail detail
                    = new tech.coinbub.daemon.normalization.model.TransactionDetail();
            detail.address = rawDetail.address;
            detail.amount = rawDetail.amount;
            detail.fee = rawDetail.fee;
            result.details.add(detail);
        }

        return result;
    }
}
