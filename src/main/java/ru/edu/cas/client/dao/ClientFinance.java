package ru.edu.cas.client.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "finance")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientFinance {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column(name = "revenue")
    private int revenue;

    @Column(name = "staf")
    private int staf;

    @Column(name = "cost_price")
    private int costPrice;

    @Column(name = "assets")
    private int assets;

    @Column(name = "reserves")
    private int reserves;

    @Column(name = "profit")
    private int profit;

    @Column(name = "loans")
    private int loans;

    @Column(name = "date")
    private String date ;
}
