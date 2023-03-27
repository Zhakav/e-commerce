package com.zhakav.ecommerce;

import com.zhakav.ecommerce.entity.*;
import com.zhakav.ecommerce.repository.*;
import com.zhakav.ecommerce.service.OrderingServiceImp;
import com.zhakav.ecommerce.service.PurchasingService;
import com.zhakav.ecommerce.service.PurchasingServiceImp;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchasingServiceTest {

    @Mock
    ShoppingSessionRepository sessionRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    OrderDetailRepository orderDetailRepository;
    @Mock
    PaymentDetailRepository paymentRepository;
    @Mock
    ProductInventoryRepository inventoryRepository;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    PurchasingServiceImp purchasingService;

    @Test
    public void startPurchasingTest(){

        //Arrange

        //Product
        Product product1=new Product("TV","Some Tv","hdk5865d4dd",10000000L,"",new ArrayList<String>(),true);
        Product product2=new Product("Table","Some Table","hdk5865d4dd",1000000L,"",new ArrayList<String>(),true);

        //cartItems
        CartItem cartItem1=new CartItem(5);
        cartItem1.setProduct(product1);

        CartItem cartItem2=new CartItem(10);
        cartItem2.setProduct(product2);

        List<CartItem> cartItems= Arrays.asList(cartItem1,cartItem2);

        //Session
        ShoppingSession session=new ShoppingSession();
        session.setId(1);
        session.setCartItems(cartItems);
        session.setTotal(60000000L);

        //Session find by user id method
        when(sessionRepository.findByUserId(1)).thenReturn(Optional.of(session));
        when(cartItemRepository.findBySessionId(1)).thenReturn(cartItems);

        //Act
        OrderDetail orderDetail=purchasingService.startPurchasing(1);

        //Assert
        verify(orderDetailRepository,times(1)).save(any(OrderDetail.class));
        assertEquals(orderDetail.getTotal(),session.getTotal());
        assertEquals(orderDetail.getOrderItems().get(0).getQuantity(),5);
        assertEquals(orderDetail.getOrderItems().get(1).getQuantity(),10);
        assertEquals(orderDetail.getOrderItems().get(0).getProduct(),product1);
        assertEquals(orderDetail.getOrderItems().get(1).getProduct(),product2);



    }

    @Test
    public void setOrderItemsTest(){

        //Arrange

        //Product
        Product product1=new Product("TV","Some Tv","hdk5865d4dd",10000000L,"",new ArrayList<String>(),true);
        Product product2=new Product("Table","Some Table","hdk5865d4dd",1000000L,"",new ArrayList<String>(),true);

        //cartItems
        CartItem cartItem1=new CartItem(5);
        cartItem1.setProduct(product1);

        CartItem cartItem2=new CartItem(10);
        cartItem2.setProduct(product2);

        List<CartItem> cartItems= Arrays.asList(cartItem1,cartItem2);

        //findBySessionId
        when(cartItemRepository.findBySessionId(1)).thenReturn((List<CartItem>) cartItems);

        //OrderDetail
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setTotal(60000000L);

        ///Act
        List<OrderItem> orderItems=purchasingService.setOrderItems(orderDetail,1);

        ///Assert
        verify(orderItemRepository,times(2)).save(any(OrderItem.class));

        assertEquals(orderItems.get(0).getProduct(),product1);
        assertEquals(orderItems.get(1).getProduct(),product2);

        assertEquals(orderItems.get(0).getQuantity(),cartItem1.getQuantity());
        assertEquals(orderItems.get(1).getQuantity(),cartItem2.getQuantity());

        assertEquals(orderItems.get(0).getOrderDetail(),orderDetail);
        assertEquals(orderItems.get(1).getOrderDetail(),orderDetail);


    }

}
