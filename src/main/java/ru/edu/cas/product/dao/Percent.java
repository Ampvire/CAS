package ru.edu.cas.product.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "percent")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Percent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "years")
    private int years;
    @Column(name = "percent")
    private int percent;
}
