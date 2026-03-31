package com.astro.dto.workflow.ProjectMaster;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProjectMasterRequestDTO {

    private String projectCode;
    private String projectNameDescription;
    private String financialYear;
    private BigDecimal allocatedAmount;
    private BigDecimal availableProjectLimit;
    private String departmentDivision;
    private String budgetType;
    private String startDate;
    private String  endDate;
    private String remarksNotes;
    private String projectHead; // Employee ID of the project head
    private String projectHeadName; // Name of the project head

    // New Admin Panel fields
    private String status;
    private String category;
    // Accept either a single code (String) or multiple codes from frontend (List)
    private String budgetCode;
    private List<String> budgetCodes; // multi-select from frontend

    private String updatedBy;
    private String createdBy;

    /** Returns the effective budget code: joins budgetCodes list if present, otherwise uses budgetCode. */
    public String getEffectiveBudgetCode() {
        if (budgetCodes != null && !budgetCodes.isEmpty()) {
            return String.join(",", budgetCodes);
        }
        return budgetCode;
    }
}
