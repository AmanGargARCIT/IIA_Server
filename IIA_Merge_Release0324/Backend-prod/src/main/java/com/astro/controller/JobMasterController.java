package com.astro.controller;

import com.astro.dto.workflow.ApprovalAndRejectionRequestDTO;
import com.astro.dto.workflow.JobMasterRequestDto;
import com.astro.dto.workflow.JobMasterResponseDto;
import com.astro.service.JobMasterService;
import com.astro.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-master")
public class JobMasterController {

    @Autowired
    private JobMasterService jobMasterService;


    @PostMapping
    public ResponseEntity<Object> createJobMaster(@RequestBody JobMasterRequestDto requestDTO) {
        JobMasterResponseDto material = jobMasterService.createJobMaster(requestDTO);
        return new ResponseEntity<Object>(ResponseBuilder.getSuccessResponse(material), HttpStatus.OK);
    }

    @PutMapping("/{jobCode}")
    public ResponseEntity<Object> updateJobMaster(@PathVariable String jobCode,
                                                      @RequestBody JobMasterRequestDto requestDTO) {
        JobMasterResponseDto response =jobMasterService.updateJobMaster(jobCode, requestDTO);
        return new ResponseEntity<Object>(ResponseBuilder.getSuccessResponse(response), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Object> getAllJobMaster() {
        List<JobMasterResponseDto> response = jobMasterService.getAllJobMasters();
        return new ResponseEntity<Object>(ResponseBuilder.getSuccessResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{jobCode}")
    public ResponseEntity<Object> getJobMasterById(@PathVariable String jobCode) {
        JobMasterResponseDto responseDTO = jobMasterService.getJobMasterById(jobCode);
        return new ResponseEntity<Object>(ResponseBuilder.getSuccessResponse(responseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{jobCode}")
    public ResponseEntity<String> deleteJobMaster(@PathVariable String jobCode) {
       jobMasterService.deleteJobMaster(jobCode);
        return ResponseEntity.ok("job master deleted successfully. jobCode:"+" " +jobCode);
    }

    // Returns only APPROVED jobs — used by indent creation page
    @GetMapping("/approved")
    public ResponseEntity<Object> getAllApprovedJobMasters() {
        List<JobMasterResponseDto> response = jobMasterService.getAllApprovedJobMasters();
        return new ResponseEntity<>(ResponseBuilder.getSuccessResponse(response), HttpStatus.OK);
    }

    // Search approved jobs by keyword (jobCode or description) — used by JobForm search
    @GetMapping("/search")
    public ResponseEntity<Object> searchJobs(@RequestParam("keyword") String keyword) {
        List<JobMasterResponseDto> results = jobMasterService.searchJobs(keyword);
        return new ResponseEntity<>(ResponseBuilder.getSuccessResponse(results), HttpStatus.OK);
    }

    // SPO approve/reject action endpoint
    @PostMapping("/performAction")
    public ResponseEntity<Object> performAction(@RequestBody ApprovalAndRejectionRequestDTO request) {
        String result = jobMasterService.performActionForJob(request);
        return new ResponseEntity<>(ResponseBuilder.getSuccessResponse(result), HttpStatus.OK);
    }


}
