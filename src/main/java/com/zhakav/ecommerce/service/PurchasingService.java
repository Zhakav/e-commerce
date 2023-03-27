package com.zhakav.ecommerce.service;

import com.zhakav.ecommerce.entity.OrderDetail;
import com.zhakav.ecommerce.entity.OrderItem;
import com.zhakav.ecommerce.entity.PaymentDetail;

import java.util.List;

public interface PurchasingService {

    public OrderDetail startPurchasing(long userId);
    public void endPurchasing(String status, long userId);
    public List<OrderItem> setOrderItems(OrderDetail orderDetail, long sessionId);
    public void successfulPurchase(long userId);
    
}
