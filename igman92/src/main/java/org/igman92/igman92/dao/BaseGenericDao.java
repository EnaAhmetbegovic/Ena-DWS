package org.igman92.igman92.dao;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseGenericDao<T extends IBaseEntity> extends BaseAbstractDao<T> {
}
