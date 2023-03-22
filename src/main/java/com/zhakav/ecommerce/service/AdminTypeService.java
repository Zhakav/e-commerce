package com.zhakav.ecommerce.service;

import java.util.List;
import com.zhakav.ecommerce.entity.AdminType;

public interface AdminTypeService {
    
    public AdminType save(AdminType adminType);
    public AdminType update(AdminType adminType);
    public AdminType get(long id);
    public void delete(long id);
    public void deleteAll();
    public List<AdminType> getAll();

}
