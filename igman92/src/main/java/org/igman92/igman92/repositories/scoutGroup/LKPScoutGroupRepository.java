package org.igman92.igman92.repositories.scoutGroup;

import org.igman92.igman92.entity.impl.lkp.organization.LKPScoutGroup;
import org.springframework.data.repository.Repository;

public interface LKPScoutGroupRepository extends Repository<LKPScoutGroup, Long> {

    LKPScoutGroup findById(Short id);
}
