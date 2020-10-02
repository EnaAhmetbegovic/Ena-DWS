package org.igman92.igman92.repositories.scoutGroup;

import org.igman92.igman92.entity.impl.lkp.organization.LKPScoutGroup;
import org.igman92.igman92.entity.impl.organization.ScoutGroup;
import org.springframework.data.repository.Repository;

public interface ScoutGroupRepository extends Repository<ScoutGroup, Long> {

    ScoutGroup findById(Short id);
    ScoutGroup findByScoutGroup(LKPScoutGroup scoutGroup);
}
