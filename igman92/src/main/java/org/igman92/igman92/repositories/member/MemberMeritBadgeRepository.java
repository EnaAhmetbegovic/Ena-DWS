package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberMeritBadge;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberMeritBadgeRepository extends Repository<MemberMeritBadge, Long> {

    List<MemberMeritBadge> findByMemberId(Long memberId);
}
