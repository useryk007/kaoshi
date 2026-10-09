package org.example.dormrepairsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.dormrepairsystem.dto.DeviceTypeStatsDTO;
import org.example.dormrepairsystem.entity.RepairOrder;

import java.util.List;

/**
 * 报修单核心Mapper
 */
public interface RepairOrderMapper extends BaseMapper<RepairOrder> {

    @Select("SELECT device_type, COUNT(*) AS order_count FROM repair_order "
	    + "GROUP BY device_type ORDER BY order_count DESC")
    @Results({
	    @Result(column = "device_type", property = "deviceType"),
	    @Result(column = "order_count", property = "count")
    })
    List<DeviceTypeStatsDTO> selectDeviceTypeStats();
}