package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberWoodbadge;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberWoodbadgeRepository extends Repository<MemberWoodbadge, Long>  {

    List<MemberWoodbadge> findByMemberId(Long memberId);
}
