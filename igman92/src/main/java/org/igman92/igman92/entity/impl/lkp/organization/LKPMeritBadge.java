package org.igman92.igman92.entity.impl.lkp.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LKP_merit_badge")
public class LKPMeritBadge extends LookUpEntity {

    @ManyToOne
    @NotNull
    private LKPCategory category;

    public LKPCategory getCategory() {
        return category;
    }

    public void setCategory(LKPCategory category) {
        this.category = category;
    }
}
