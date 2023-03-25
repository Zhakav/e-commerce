package com.zhakav.ecommerce.service;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.OrderDetail;
import com.zhakav.ecommerce.repository.OrderDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDetailServiceImp implements OrderDetailService{

    OrderDetailRepository repository;

    @Override
    public List<OrderDetail> getByUser(long userId) {

        return repository.findByUserId(userId);

    }

}
