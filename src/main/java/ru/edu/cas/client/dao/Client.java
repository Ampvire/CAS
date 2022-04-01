package ru.edu.cas.client.dao;

import lombok.*;
import ru.edu.cas.user.dao.User;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "inn")
    private String inn;
    @Column(name = "ogrn")
    private String ogrn;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private ClientSegment segmentId;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private ClientType typeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
