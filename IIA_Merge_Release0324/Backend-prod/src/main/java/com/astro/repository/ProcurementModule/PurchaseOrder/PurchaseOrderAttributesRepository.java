package com.astro.repository.ProcurementModule.PurchaseOrder;

import com.astro.dto.workflow.ProcurementDtos.IndentDto.materialHistoryDto;
import com.astro.dto.workflow.ProcurementDtos.performanceWarrsntySecurityReportDto;
import com.astro.dto.workflow.poMaterialHistoryDto;
import com.astro.entity.ProcurementModule.PurchaseOrderAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderAttributesRepository extends JpaRepository<PurchaseOrderAttributes, Long> {

   // @Query("SELECT poa.quantity FROM PurchaseOrderAttributes poa WHERE poa.poId = :poId AND poa.materialCode = :materialCode")
   // Optional<BigDecimal> findQuantityByPoIdAndMaterialCode(@Param("poId") String poId, @Param("materialCode") String materialCode);
   @Query("SELECT poa.quantity FROM PurchaseOrderAttributes poa WHERE poa.purchaseOrder.poId = :poId AND poa.materialCode = :materialCode")
   Optional<BigDecimal> findQuantityByPoIdAndMaterialCode(@Param("poId") String poId, @Param("materialCode") String materialCode);


   // Optional<PurchaseOrderAttributes> findByPoIdAndMaterialCode(String poId, String materialCode);
   Optional<PurchaseOrderAttributes> findByPurchaseOrder_PoIdAndMaterialCode(String poId, String materialCode);

   @Query("""
        SELECT new com.astro.dto.workflow.poMaterialHistoryDto(
            po.poId,
            po.createdDate,
            po.vendorName,
            po.totalValueOfPo
        )
        FROM PurchaseOrderAttributes attr
        JOIN attr.purchaseOrder po
        WHERE attr.materialCode = :materialCode
          AND po.createdDate >= :fromDate
        ORDER BY po.createdDate DESC
        """)
   List<poMaterialHistoryDto> findLatestPOByMaterialCode(
           @Param("materialCode") String materialCode,
           @Param("fromDate") LocalDateTime fromDate
   );

   @Query("SELECT p.gst FROM PurchaseOrderAttributes p WHERE p.materialCode = :materialCode AND p.purchaseOrder.poId = :poId")
   BigDecimal findGstByMaterialCodeAndPoId(@Param("materialCode") String materialCode,
                                           @Param("poId") String poId);

   @Query("SELECT p.currency FROM PurchaseOrderAttributes p WHERE p.materialCode = :materialCode AND p.purchaseOrder.poId = :poId")
   String findCurrencyByMaterialCodeAndPoId(@Param("materialCode") String materialCode,
                                            @Param("poId") String poId);

   @Query("SELECT p.exchangeRate FROM PurchaseOrderAttributes p WHERE p.materialCode = :materialCode AND p.purchaseOrder.poId = :poId")
   BigDecimal findExchangeRateByMaterialCodeAndPoId(@Param("materialCode") String materialCode,
                                                    @Param("poId") String poId);

   @Query("""
        SELECT new com.astro.dto.workflow.MaterialPurchaseHistoryDTO(
            po.poId,
            po.indentId,
            po.vendorName,
            po.vendorId,
            attr.quantity,
            attr.rate,
            attr.currency,
            attr.exchangeRate,
            po.createdDate,
            attr.materialCode,
            attr.materialDescription,
            attr.gst,
            attr.totalPoMaterialPriceInInr
        )
        FROM PurchaseOrderAttributes attr
        JOIN attr.purchaseOrder po
        WHERE attr.materialCode = :materialCode
        ORDER BY po.createdDate DESC
        """)
   List<com.astro.dto.workflow.MaterialPurchaseHistoryDTO> findPurchaseHistoryByMaterialCode(@Param("materialCode") String materialCode);

   @Query(value = """
        SELECT po.po_id, po.indent_id, po.vendor_name, po.vendor_id,
               attr.quantity, attr.rate, attr.currency, attr.exchange_rate,
               po.created_date, attr.material_code, attr.material_description,
               attr.gst, attr.total_po_material_price_in_inr
        FROM purchase_order_attributes attr
        JOIN purchase_order po ON attr.po_id = po.po_id
        WHERE po.indent_id IN (
            SELECT DISTINCT jd.indent_id FROM job_details jd WHERE jd.job_code = :jobCode
        )
        ORDER BY po.created_date DESC
        """, nativeQuery = true)
   List<Object[]> findPurchaseHistoryByJobCodeRaw(@Param("jobCode") String jobCode);

 /*  @Query("""
SELECT new com.astro.dto.workflow.ProcurementDtos.IndentDto.materialHistoryDto(
    po.poId,
    CAST(po.createdDate AS string),
    po.vendorName
)
FROM PurchaseOrderAttributes attr
JOIN attr.purchaseOrder po
WHERE attr.materialCode = :materialCode
ORDER BY po.createdDate DESC
""")
   List<materialHistoryDto> findLatestPOByMaterialCode(@Param("materialCode") String materialCode, Pageable pageable);
*/



}
