package services.interfaces;

import entities.Transaction;

import java.util.List;

public interface TransactionInterface {
    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    Transaction updateTransaction(Long id, Transaction transaction);

    void deleteTransaction(Long id);

}
