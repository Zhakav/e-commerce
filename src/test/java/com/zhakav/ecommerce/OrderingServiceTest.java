package com.zhakav.ecommerce;

import com.zhakav.ecommerce.entity.*;
import com.zhakav.ecommerce.repository.CartItemRepository;
import com.zhakav.ecommerce.repository.ShoppingSessionRepository;
import com.zhakav.ecommerce.repository.UserRepository;
import com.zhakav.ecommerce.service.OrderingServiceImp;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderingServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private ShoppingSessionRepository sessionRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderingServiceImp orderingService;

    @Test
    public void calculateTotalTest(){

        ///Arrange

        //products
        Product product1=new Product("TV","Some Tv","hdk5865d4dd",10000000L,"", new ArrayList<>(),true);
        Product product2=new Product("Table","Some Table","hdk5865d4dd",1000000L,"", new ArrayList<>(),true);

        //discount
        Discount discount1=new Discount("discount 1", 0.1F,true);

        product1.setDiscount(discount1);

        //cartItems
        CartItem cartItem1=new CartItem(5);
        cartItem1.setProduct(product1);

        CartItem cartItem2=new CartItem(10);
        cartItem2.setProduct(product2);

        List<CartItem> cartItems=Arrays.asList(cartItem1,cartItem2);

        //Act
        long total=orderingService.calculateTotal(cartItems);

        //Assert
        assertEquals(55000000L,total);

    }

    @Test
    public void saveCartItemsToSessionTest(){

        ///Arrange

        //User
        User user=new User("zhakav","3Mosina@1982","zhakav","hesari","09387198078","Men");

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
        ShoppingSession session=new ShoppingSession(60000000L);
        session.setUser(user);

        ///Act
        session=orderingService.saveCartItemsToSession(cartItems,session);

        ///Assert

        assertEquals(session.getCartItems().contains(cartItem1),true);
        assertEquals(session.getCartItems().contains(cartItem2),true);
        assertEquals(cartItem1.getSession(),session);
        assertEquals(cartItem2.getSession(),session);
        verify(cartItemRepository,times(1)).saveAll(cartItems);

    }

    @Test
    public void orderTest(){

        ///Arrange

        //User
        Optional<User> user= Optional.of(new User("zhakav", "3Mosina@1982", "zhakav", "hesari", "09387198078", "Men"));

        //findById
        when(userRepository.findById(1L)).thenReturn(user);

        //Product
        Product product1=new Product("TV","Some Tv","hdk5865d4dd",10000000L,"",new ArrayList<String>(),true);
        Product product2=new Product("Table","Some Table","hdk5865d4dd",1000000L,"",new ArrayList<String>(),true);

        //cartItems
        CartItem cartItem1=new CartItem(5);
        cartItem1.setProduct(product1);

        CartItem cartItem2=new CartItem(10);
        cartItem2.setProduct(product2);

        List<CartItem> cartItems=Arrays.asList(cartItem1,cartItem2);

        ///Act
        ShoppingSession session=orderingService.order(cartItems,1);

        //Assert
        verify(sessionRepository,times(1)).save(session);
        assertEquals(session.getUser(),user.get());
        assertEquals(session.getTotal(),60000000);

    }

}
