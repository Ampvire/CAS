package ru.edu.cas.clients.dao;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="finance")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Finance {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "client_id")
    private int clientId;
    @Column(name = "revenue")
    private long revenue;
    @Column(name = "staf")
    private int staf;
    @Column(name = "assets")
    private long assets;
    @Column(name = "cost_price")
    private long costPrice;
    @Column(name = "reserves")
    private long reserves;
    @Column(name = "profit")
    private long profit;
    @Column(name = "loans")
    private long loans;
    @Column(name = "date")
    private String date;
}
