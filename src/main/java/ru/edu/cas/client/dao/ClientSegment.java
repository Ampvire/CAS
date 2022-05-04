package ru.edu.cas.client.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client_segment")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientSegment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "segment")
    private String segment;
}
