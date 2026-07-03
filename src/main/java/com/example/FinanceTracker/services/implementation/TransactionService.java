package com.example.FinanceTracker.services.implementation;

import com.example.FinanceTracker.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinanceTracker.repositories.TransactionRepository;
import com.example.FinanceTracker.services.interfaces.TransactionInterface;

import java.util.List;

@Service
public class TransactionService implements TransactionInterface {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction transaction = getTransactionById(id);

        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAmount(updatedTransaction.getAmount());

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
