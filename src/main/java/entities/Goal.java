package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goalId;

    @Column(name = "title")
    private String title;

    @Column(name = "target_amount")
    private double targetAmount;

    @Column(name = "current_amount")
    private double currentAmount;

    @Column(name = "deadline")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userGoal;

}
