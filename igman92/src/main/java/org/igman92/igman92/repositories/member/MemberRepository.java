package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.Member;
import org.springframework.data.repository.Repository;

public interface MemberRepository  extends Repository<Member, Long> {

    Member findByIdCardNumber(Integer id);
    Member findById(Long id);
}
