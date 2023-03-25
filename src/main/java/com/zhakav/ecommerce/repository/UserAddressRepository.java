package com.zhakav.ecommerce.repository;

import java.util.List;
import java.util.Set;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.zhakav.ecommerce.entity.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress,Long> {
    public List<UserAddress> findByUserId(long userId);

    @Transactional
    public void deleteByUserId(long userId);
}
