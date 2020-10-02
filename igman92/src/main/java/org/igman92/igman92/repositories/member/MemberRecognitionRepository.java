package org.igman92.igman92.repositories.member;

import org.igman92.igman92.entity.impl.member.MemberRecognition;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberRecognitionRepository extends Repository<MemberRecognition, Long> {

    List<MemberRecognition> findByMemberId(Long memberId);
}
