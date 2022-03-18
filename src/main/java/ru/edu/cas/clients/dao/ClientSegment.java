package ru.edu.cas.clients.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_segment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientSegment {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "segment")
    private String segment;
}
