package com.project.demo.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyUpdateRequest {

    private String name;

    private String specification;

    private String provider;

    // Trường cấm - dùng để detect client gửi lên
    private Long id;

    private Integer quantity;
}
