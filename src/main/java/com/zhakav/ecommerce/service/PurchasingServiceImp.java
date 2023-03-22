package com.zhakav.ecommerce.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zhakav.ecommerce.entity.CartItem;
import com.zhakav.ecommerce.entity.OrderDetail;
import com.zhakav.ecommerce.entity.OrderItem;
import com.zhakav.ecommerce.entity.PaymentDetail;
import com.zhakav.ecommerce.entity.ShoppingSession;
import com.zhakav.ecommerce.repository.CartItemRepository;
import com.zhakav.ecommerce.repository.OrderDetailRepository;
import com.zhakav.ecommerce.repository.OrderItemRepository;
import com.zhakav.ecommerce.repository.PaymentDetailRepository;
import com.zhakav.ecommerce.repository.ShoppingSessionRepository;

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

    @Override
    public void startPurchasing(long userId) {

        OrderDetail orderDetail=new OrderDetail();
        ShoppingSession session= OrderingServiceImp.unwrapSession(sessionRepository.findByUserId(userId), userId);

        setOrderItems(orderDetail, userId);
        orderDetail.setTotal(session.getTotal());

        orderDetailRepository.save(orderDetail);

    }

    @Override
    public void endPurchasing(String status, long userId) {

        if(status == "Successful"){

            deleteSession(userId);

        }

        PaymentDetail payment=new PaymentDetail();
        OrderDetail orderDetail=unwrap(orderDetailRepository.findByUserId(userId), userId);

        payment.setAmount(orderDetail.getTotal());
        payment.setProvider("provider");

        orderDetail.setPaymentDetail(payment);
        payment.setOrderDetail(orderDetail);

        paymentRepository.save(payment);
        orderDetailRepository.save(orderDetail);


    }

    private void setOrderItems( OrderDetail orderDetail,long sessionId){

        List<CartItem> cartItems= cartItemRepository.findBySessionId(sessionId);
        List<OrderItem> orderItems=Arrays.asList(null);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem=new OrderItem();

            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrderDetail(orderDetail);
            orderItems.add(orderItem);
            orderItemRepository.save(orderItem);
        }

        orderDetail.setOrderItems(orderItems);

    }

    private void deleteSession(long userId){

        long sessionId=OrderingServiceImp.unwrapSession(sessionRepository.findByUserId(userId), userId).getId();

        cartItemRepository.deleteAllBySessionId(sessionId);
        sessionRepository.deleteById(sessionId);

    }
    
    public static OrderDetail unwrap(Optional<OrderDetail> orderDetail , long id){
    
        if(orderDetail.isPresent())
            return orderDetail.get();
        else
            throw new RuntimeException("Cannot find order detail with id : " + id);

    }
}
 