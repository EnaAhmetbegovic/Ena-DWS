package org.igman92.igman92.entity.impl.lkp.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LKP_scout_association")
public class LKPScoutAssociation extends LookUpEntity {

    @Column
    @NotNull
    private String translation;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
