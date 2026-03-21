package com.project.demo.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopExportDTO {
    private Long topSupplyId;
    private String topSupplyName;
    private Long totalExportQuantity;
}
