package org.example.dormrepairsystem.dto;

import lombok.Data;

/**
 * 按设备类型聚合的报修单数量
 */
@Data
public class DeviceTypeStatsDTO {
    private String deviceType;
    private Long count;
}