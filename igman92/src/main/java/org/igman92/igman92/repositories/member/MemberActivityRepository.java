package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberActivity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberActivityRepository extends Repository<MemberActivity, Long> {

    List<MemberActivity> findByMemberId(Long memberId);
}
