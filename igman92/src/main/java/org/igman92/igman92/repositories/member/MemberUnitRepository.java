package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberUnit;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberUnitRepository extends Repository<MemberUnit, Long> {

    List<MemberUnit> findByMemberId(Long memberId);
}


    

