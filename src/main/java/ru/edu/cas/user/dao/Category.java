package ru.edu.cas.user.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;

}
