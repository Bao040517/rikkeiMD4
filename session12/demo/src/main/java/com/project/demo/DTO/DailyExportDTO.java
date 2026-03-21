package com.project.demo.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyExportDTO {
    private Long supplyId;
    private String supplyName;
    private Long totalExportQuantity;
}
