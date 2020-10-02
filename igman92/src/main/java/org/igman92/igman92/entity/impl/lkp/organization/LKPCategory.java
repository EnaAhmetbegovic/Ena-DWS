package org.igman92.igman92.entity.impl.lkp.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LKP_category")
public class LKPCategory extends LookUpEntity {

    @Column(name = "start_age")
    @NotNull
    private Integer startAge;

    @Column(name = "end_age")
    @NotNull
    private Integer endAge;

    @ManyToOne
    @NotNull
    private LKPUnit unit;

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public LKPUnit getUnit() {
        return unit;
    }

    public void setUnit(LKPUnit unit) {
        this.unit = unit;
    }
}
