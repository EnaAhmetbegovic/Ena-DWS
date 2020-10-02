package org.igman92.igman92.dao;

import java.io.Serializable;
import java.util.Date;

public interface IBaseEntity {

    Serializable getId();

    Date getLastUpdated();
}
