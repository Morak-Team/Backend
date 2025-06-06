package com.example.seoulpublicdata2025backend.domain.company.dao;

import com.example.seoulpublicdata2025backend.domain.company.dto.CompanyMapDto;
import com.example.seoulpublicdata2025backend.domain.company.dto.CompanyPreviewDto;
import com.example.seoulpublicdata2025backend.domain.company.entity.Company;
import com.example.seoulpublicdata2025backend.domain.company.dto.CompanyLocationTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyId(@Param("companyId") Long companyId);

    @Query("""
    SELECT new com.example.seoulpublicdata2025backend.domain.company.dto.CompanyMapDto(
        c.companyId,
        c.companyName,
        c.location.latitude,
        c.location.longitude,
        c.companyCategory
    )
    FROM Company c
    """)
    List<CompanyMapDto> findAllCompanyMapData();
           
    @Query("SELECT new com.example.seoulpublicdata2025backend.domain.company.dto.CompanyLocationTypeDto("
            + "c.companyId, c.companyLocation, c.companyType, c.companyCategory, c.location)"
            + "FROM Company c "
            + "WHERE c.companyId = :companyId")
    Optional<CompanyLocationTypeDto> findCompanyLocationTypeByCompanyId(@Param("companyId") Long companyId);
}
