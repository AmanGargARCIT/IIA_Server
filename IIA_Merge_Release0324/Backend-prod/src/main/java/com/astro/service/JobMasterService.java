package com.astro.service;

import com.astro.dto.workflow.ApprovalAndRejectionRequestDTO;
import com.astro.dto.workflow.JobMasterRequestDto;
import com.astro.dto.workflow.JobMasterResponseDto;


import java.util.List;

public interface JobMasterService {

    public JobMasterResponseDto createJobMaster(JobMasterRequestDto jobMasterRequestDto);
    public JobMasterResponseDto  updateJobMaster(String jobCode,JobMasterRequestDto jobMasterRequestDto);
    public List<JobMasterResponseDto > getAllJobMasters();
    public List<JobMasterResponseDto > getAllApprovedJobMasters();
    public List<JobMasterResponseDto > getAllAwaitingApprovalJobs();
    public List<JobMasterResponseDto > getAllChangeRequestJobs();
    public String performActionForJob(ApprovalAndRejectionRequestDTO request);

    public JobMasterResponseDto  getJobMasterById(String jobCode);
    public void deleteJobMaster(String jobCode);
    public List<JobMasterResponseDto> searchJobs(String keyword);


}
