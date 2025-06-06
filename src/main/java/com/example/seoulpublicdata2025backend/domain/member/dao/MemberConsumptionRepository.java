package com.example.seoulpublicdata2025backend.domain.member.dao;

import com.example.seoulpublicdata2025backend.domain.company.dto.CompanyLocationTypeDto;
import com.example.seoulpublicdata2025backend.domain.company.entity.CompanyType;
import com.example.seoulpublicdata2025backend.domain.member.entity.Member;
import com.example.seoulpublicdata2025backend.domain.member.entity.MemberConsumption;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberConsumptionRepository extends JpaRepository<MemberConsumption, Long> {
    Optional<MemberConsumption> findByMemberAndCompanyType(Member member, CompanyType companyType);
    @Query("SELECT m FROM MemberConsumption m WHERE m.member.kakaoId = :kakaoId")
    List<MemberConsumption> findMemberConsumptionByKakaoId(@Param("kakaoId") Long kakaoId);

    @Query("SELECT m FROM MemberConsumption m WHERE m.member.kakaoId = :kakaoId AND m.companyType = :companyType")
    Optional<MemberConsumption> findConsumptionByKakaoIdAndCompanyType(
            @Param("kakaoId") Long kakaoId,
            @Param("companyType") CompanyType companyType
    );
}
