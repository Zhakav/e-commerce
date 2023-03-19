package com.zhakav.ecommerce.service;

import com.zhakav.ecommerce.entity.PaymentDetail;

public interface PurchasingService {

    public void startPurchasing(long userId);
    public void endPurchasing(PaymentDetail payment, long userId);
    
}
