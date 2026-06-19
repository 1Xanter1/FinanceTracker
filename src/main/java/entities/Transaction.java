package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    public enum TransactionType{
        INCOME,
        EXPENSE
    }

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
