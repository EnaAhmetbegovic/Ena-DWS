package org.igman92.igman92.entity.impl.lkp.place;

import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LKP_entity", uniqueConstraints = { @UniqueConstraint(columnNames = { "_key" }) })
public class LKPEntity extends LookUpEntity {
}
