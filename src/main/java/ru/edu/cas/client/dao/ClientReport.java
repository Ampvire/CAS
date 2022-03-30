package ru.edu.cas.client.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientReport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @OneToOne
    @JoinColumn(name = "finance_id")
    private ClientFinance financeId;

    @Column(name = "profitability_sale")
    private int profitabilitySale;

    @Column(name = "inventory_turnover")
    private int inventoryTurnover;

    @Column(name = "quick_liquidity")
    private int quickLiquidity;

    @Column(name = "date")
    private String date = LocalDate.now().toString();


}
