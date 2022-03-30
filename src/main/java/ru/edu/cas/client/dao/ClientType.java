package ru.edu.cas.client.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client_type")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "note")
    private String note;
}
