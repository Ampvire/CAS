package ru.edu.cas.client.dao;

import lombok.*;
import ru.edu.cas.product.dao.Product;

import javax.persistence.*;

@Entity
@Table(name = "client_products")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
}
