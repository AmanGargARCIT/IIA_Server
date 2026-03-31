package com.astro.repository;

import com.astro.entity.JobMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMasterRepository extends JpaRepository<JobMaster, String> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(job_code, 2) AS UNSIGNED)) FROM job_master WHERE job_code REGEXP '^J[0-9]{6}$'", nativeQuery = true)
    Long findMaxJobSequence();

    boolean existsByCategoryAndSubCategoryAndJobDescriptionIgnoreCase(String category, String subCategory, String jobDescription);

    List<JobMaster> findByApprovalStatus(String approvalStatus);

    @Query("SELECT j FROM JobMaster j WHERE j.approvalStatus = 'APPROVED' AND (LOWER(j.jobCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.jobDescription) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<JobMaster> searchApprovedJobs(@Param("keyword") String keyword);

}
