package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberCategory;
import org.springframework.data.repository.Repository;

public interface MemberCategoryRepository extends Repository<MemberCategory, Long> {

    MemberCategory findByMemberIdAndActive(Long memberId, Boolean active);
}
