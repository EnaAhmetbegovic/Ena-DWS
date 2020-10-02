package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Person person;

    @Column(name = "id_card_number")
    @NotNull
    private Integer idCardNumber;

    @Column(name = "record_number")
    private String recordNumber;

    @Column(name = "leader")
    private Integer leader;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Integer idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }
}
