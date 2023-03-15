package com.zhakav.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.Discount;
import com.zhakav.ecommerce.entity.Product;
import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.entity.ProductInventory;
import com.zhakav.ecommerce.entity.Supplier;
import com.zhakav.ecommerce.repository.DiscountRepository;
import com.zhakav.ecommerce.repository.ProductCategoryRepository;
import com.zhakav.ecommerce.repository.ProductInventoryRepository;
import com.zhakav.ecommerce.repository.ProductRepository;
import com.zhakav.ecommerce.repository.SupplierRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    ProductRepository repository;
    ProductInventoryRepository inventoryRepository;
    ProductCategoryRepository categoryRepository;
    SupplierRepository supplierRepository;
    DiscountRepository discountRepository;

    @Override
    public Product save(Product product, long inventoryId, long categoryId, long supplierId) {

        ProductInventory inventory=ProductInventoryServiceImp.unwrap(
            inventoryRepository.findById(inventoryId), inventoryId);

        product.setInventory(inventory);

        ProductCategory category=ProductCategoryServiceImp.unwrap(
            categoryRepository.findById(categoryId), categoryId);

        product.setCategory(category);

        Supplier supplier=SupplierServiceImp.unwrap(
            supplierRepository.findById(supplierId), supplierId);

        product.setSupplier(supplier);

        return repository.save(product);

    }

    @Override
    public Product update(Product product, long inventoryId, long categoryId, long supplierId) {
        
        return save(product, inventoryId, categoryId, supplierId);

    }

    @Override
    public Product get(long id) {
        return unwrap(repository.findById(id), id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void setDiscount(long productId,long discountId) {

        Product product=get(productId);

        Discount discount=DiscountServiceImp.unwrap(discountRepository.findById(discountId), discountId);

        product.setDiscount(discount);
        
    }

    @Override
    public Product getByInventory(long inventoryId) {
        return unwrap(repository.findByInventoryId(inventoryId), inventoryId);
    }

    @Override
    public List<Product> getByCategory(long categoryId) {

        return (List<Product>) repository.findByCategoryId(categoryId);

    }

    @Override
    public List<Product> getByDiscount(long discountId) {

        return (List<Product>) repository.findByDiscountId(discountId);

    }

    @Override
    public List<Product> getBySupplier(long supplierId) {

        return (List<Product>) repository.findBySupplierId(supplierId);

    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) repository.findAll();
    }

    public static Product unwrap(Optional<Product> product , long id){
    
        if(product.isPresent())
            return product.get();
        else
            throw new RuntimeException("Cannot find product with id : " + id);

    }
    
}
