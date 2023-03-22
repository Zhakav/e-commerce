package com.zhakav.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.Product;
import com.zhakav.ecommerce.entity.ProductCategory;
import com.zhakav.ecommerce.entity.ProductInventory;
import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.entity.User;
import com.zhakav.ecommerce.repository.CartItemRepository;
import com.zhakav.ecommerce.repository.ProductCategoryRepository;
import com.zhakav.ecommerce.repository.ProductInventoryRepository;
import com.zhakav.ecommerce.repository.ProductRepository;
import com.zhakav.ecommerce.repository.ShoppingSessionRepository;
import com.zhakav.ecommerce.service.UserService;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class ECommerceApplication implements CommandLineRunner {

	UserService userService;

	ProductCategoryRepository categoryRepository;
	ProductInventoryRepository inventoryRepository;
	ProductRepository productRepository;

	ShoppingSessionRepository sessionRepository;
	CartItemRepository cartItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		//User
		User user=new User("zhakav","3Mosina@1982","zhakav","hesari","09387198078");
		userService.save(user);

		//Product
		ProductCategory category=new ProductCategory("Category1","This is category 1");
		ProductInventory inventory=new ProductInventory(5);
		Product product=new Product("TV","Some Tv","hdk5865d4dd",10000000L,"",new ArrayList<String>());

		product.setCategory(category);
		product.setInventory(inventory);
		inventory.setProduct(product);
		category.setProducts(new ArrayList<Product>());
		category.getProducts().add(product);

		productRepository.save(product);
		inventoryRepository.save(inventory);
		categoryRepository.save(category);

		//Session
		CartItem cartItem1=new CartItem(10);
		cartItem1.setProduct(product);
		ShoppingSession session=new ShoppingSession(50000000L);
		session.setUser(user);
		cartItem1.setSession(session);
		session.setCartItems(Arrays.asList(cartItem1));

		cartItemRepository.save(cartItem1);
		sessionRepository.save(session);
		*/
	}

}
