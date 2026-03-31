package com.astro.service;



import com.astro.dto.workflow.UomMasterRequestDto;
import com.astro.dto.workflow.UomMasterResponseDto;
// Added by Aman
import com.astro.entity.AdminPanel.LOVMaster;
// End

import java.util.List;

public interface UomMasterService {
    public UomMasterResponseDto createUomMaster(UomMasterRequestDto uomMasterRequestDto);
    public UomMasterResponseDto updateUomMaster(String uomCode,UomMasterRequestDto uomMasterRequestDto);
    public UomMasterResponseDto getUomMasterById(String uomCode);
    public List<UomMasterResponseDto> getAllUomMasters();
    public void deleteUomMaster(String uomCode);
     // Added by aman
    public void updateFromLOV(LOVMaster updated);
    public void createFromLOV(LOVMaster saved);
    // End
}
