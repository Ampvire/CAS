package ru.edu.cas.clients.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientType {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String clientType;
    @Column(name = "note")
    private String clientTypeLong;
}

