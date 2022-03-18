package ru.edu.cas.clients.dao;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Clients {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String clientName;
    @Column(name = "INN")
    private long inn;
    @Column(name = "OGRN")
    private long ogrn;
    @Column(name = "segment_iD")
    private int segmentId;
    @Column(name = "type_id")
    private int typeId;

    /**
     * Определить сегмент клиента(идентификатор)
     * @param staf -численность сотрудников
     * @param revenue -выручка
     * */
    public int calcSegmentId(int staf, long revenue){
        if(staf < 250 || revenue <= 400_000_000){
            return 1;
        }
        return 2;
    }
}
