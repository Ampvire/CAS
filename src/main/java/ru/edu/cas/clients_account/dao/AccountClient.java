package ru.edu.cas.clients_account.dao;

import lombok.*;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.user.dao.Role;

import javax.persistence.*;

@Entity
@Table(name = "account_client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountClient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;
}
