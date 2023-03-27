package com.zhakav.ecommerce.service;

import java.util.*;

import com.zhakav.ecommerce.entity.*;
import com.zhakav.ecommerce.exeption.EntityNotFoundException;
import com.zhakav.ecommerce.repository.*;
import org.springframework.stereotype.Service;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PurchasingServiceImp implements PurchasingService {

    ShoppingSessionRepository sessionRepository;
    CartItemRepository cartItemRepository;
    OrderItemRepository orderItemRepository;
    OrderDetailRepository orderDetailRepository;
    PaymentDetailRepository paymentRepository;
    ProductInventoryRepository inventoryRepository;
    ProductRepository productRepository;

    @Override
    public OrderDetail startPurchasing(long userId) {

        OrderDetail orderDetail=new OrderDetail();
        ShoppingSession session= OrderingServiceImp.unwrapSession(sessionRepository.findByUserId(userId), userId);

        setOrderItems(orderDetail, userId);
        orderDetail.setTotal(session.getTotal());
        orderDetailRepository.save(orderDetail);
        return orderDetail;

    }

    @Override
    public OrderDetail endPurchasing(String status, long orderId) {

        PaymentDetail payment=new PaymentDetail();
        OrderDetail orderDetail=unwrap(orderDetailRepository.findById(orderId), orderId);

        payment.setAmount(orderDetail.getTotal());
        payment.setStatus(status);
        payment.setProvider("provider");

        orderDetail.setPaymentDetail(payment);
        payment.setOrderDetail(orderDetail);

        paymentRepository.save(payment);
        orderDetailRepository.save(orderDetail);

        if(status == "Successful"){

            successfulPurchase(orderDetail.getUser().getId());

        }

        return orderDetail;

    }

    @Override
    public List<OrderItem> setOrderItems( OrderDetail orderDetail,long sessionId){

        List<CartItem> cartItems= cartItemRepository.findBySessionId(sessionId);
        List<OrderItem> orderItems=new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem=new OrderItem();

            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrderDetail(orderDetail);
            orderItems.add(orderItem);
            orderItemRepository.save(orderItem);
        }

        orderDetail.setOrderItems(orderItems);

        return orderItems;

    }

    @Override
    public void successfulPurchase(long userId){

        ShoppingSession session=OrderingServiceImp.unwrapSession(sessionRepository.findByUserId(userId), userId);

        List<CartItem> cartItems=session.getCartItems();

        ProductInventory inventory;
        Product product;

        for (CartItem cartItem:cartItems) {

            inventory= ProductInventoryServiceImp.unwrap(inventoryRepository.
                    findByProductId(cartItem.getProduct().getId()),cartItem.getProduct().getId());

            inventory.setQuantity(inventory.getQuantity()-cartItem.getQuantity());

            if(inventory.getQuantity()==0){

                product=inventory.getProduct();
                product.setAvailable(false);
                productRepository.save(product);

            }

            inventoryRepository.save(inventory);

        }

        //Deleting temporary shopping session and cart items
        cartItemRepository.deleteAllBySessionId(session.getId());
        sessionRepository.deleteById(session.getId());

    }
    
    public static OrderDetail unwrap(Optional<OrderDetail> orderDetail , long id){
    
        if(orderDetail.isPresent())
            return orderDetail.get();
        else
            throw new EntityNotFoundException(id,"Order Detail", "ID");

    }
}
 