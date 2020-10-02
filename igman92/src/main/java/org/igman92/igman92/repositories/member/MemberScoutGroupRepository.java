package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberScoutGroup;
import org.springframework.data.repository.Repository;

public interface MemberScoutGroupRepository extends Repository<MemberScoutGroup, Long> {

    MemberScoutGroup findByMemberId(Long memberId);
}
