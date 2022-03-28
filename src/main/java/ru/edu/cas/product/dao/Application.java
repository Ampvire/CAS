package ru.edu.cas.product.dao;

import lombok.*;
import ru.edu.cas.client.dao.Client;

import javax.persistence.*;

@Entity
@Table(name = "application")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "percent_id")
    private Percent percent;

    @Column(name = "sum")
    private int sum;
    @Column(name = "status")
    private String status;
    @Column(name = "reject_reason")
    private String rejectReason;
    @Column(name = "payment")
    private int payment;
    @Column(name = "total_amount")
    private int totalAmount;
}
