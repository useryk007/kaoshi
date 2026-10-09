package org.example.dormrepairsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dormrepairsystem.entity.OrderImage;
import org.example.dormrepairsystem.mapper.OrderImageMapper;
import org.example.dormrepairsystem.service.OrderImageService;
import org.example.dormrepairsystem.util.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class OrderImageServiceImpl extends ServiceImpl<OrderImageMapper, OrderImage> implements OrderImageService {

    @Autowired
    private FileStorage fileStorage;
    
    @Override
    public List<OrderImage> getByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderImage::getOrderId, orderId);
        return baseMapper.selectList(queryWrapper);
    }
    
    @Override
    public boolean saveOrderImage(OrderImage orderImage) {
        return save(orderImage);
    }
    
    @Override
    public boolean deleteByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderImage::getOrderId, orderId);
        return remove(queryWrapper);
    }

    @Override
    public boolean deleteImage(OrderImage orderImage) {
        if (!removeById(orderImage.getId())) {
            return false;
        }
        try {
            fileStorage.deleteImage(orderImage.getImageUrl());
        } catch (Exception e) {
            log.warn("删除图片 {} 的本地文件失败：{}", orderImage.getId(), e.getMessage());
        }
        return true;
    }
}