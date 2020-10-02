package org.igman92.igman92.entity.impl.lkp.person;

import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LKP_nationality", uniqueConstraints = { @UniqueConstraint(columnNames = {"_key"} ) })
public class LKPNationality extends LookUpEntity {
}
