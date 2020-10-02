package org.igman92.igman92.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;
import org.igman92.igman92.dao.IBaseEntity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {

    public static final int STRING_LENGTH = 250;
    public static final int STRING_LONG = 300;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    public static int getStringLength() {
        return STRING_LENGTH;
    }

    public static int getStringLong() {
        return STRING_LONG;
    }

    @Override
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
