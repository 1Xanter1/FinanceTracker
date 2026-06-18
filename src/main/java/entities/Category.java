package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    public enum CategoryType{
        INCOME,
        EXPENSE
    }

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name ="category_name")
    private String categoryName;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;
}

