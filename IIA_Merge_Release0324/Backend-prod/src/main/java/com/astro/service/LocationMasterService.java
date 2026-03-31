package com.astro.service;

import com.astro.dto.workflow.LocationMasterRequestDto;
import com.astro.dto.workflow.LocationMasterResponseDto;
// Added by Aman 
import com.astro.entity.AdminPanel.LOVMaster;
// End


import java.util.List;

public interface LocationMasterService {

    public LocationMasterResponseDto createLocationMaster(LocationMasterRequestDto locationMasterRequestDto);
    public LocationMasterResponseDto updateLocationMaster(String locationCode, LocationMasterRequestDto locationMasterRequestDto);
    public List<LocationMasterResponseDto> getAllLocationMasters();

    public LocationMasterResponseDto getLocationMasterById(String locationCode);
    public void deleteLocationMaster(String locationCode);
      // Added by Aman 
    void updateFromLOV(LOVMaster updated);
    void createFromLOV(LOVMaster saved);
    // End
}
