package com.example.FinanceTracker.services.implementation;

import com.example.FinanceTracker.entities.Category;
import com.example.FinanceTracker.entities.Transaction;
import com.example.FinanceTracker.entities.User;
import com.example.FinanceTracker.repositories.CategoryRepository;
import com.example.FinanceTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinanceTracker.repositories.TransactionRepository;
import com.example.FinanceTracker.services.interfaces.TransactionInterface;

import java.util.List;

@Service
public class TransactionService implements TransactionInterface {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        User user = userRepository.findById(
                transaction.getUser().getUserId()
        ).orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById((long) transaction.getCategory().getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setUser(user);
        transaction.setCategory(category);

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
    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
