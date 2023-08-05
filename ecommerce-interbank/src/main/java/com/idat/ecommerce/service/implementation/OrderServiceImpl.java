package com.idat.ecommerce.service.implementation;

import com.idat.ecommerce.dto.OrderDetailsRegistration;
import com.idat.ecommerce.dto.OrderDto;
import com.idat.ecommerce.model.Account;
import com.idat.ecommerce.model.OrderDetails;
import com.idat.ecommerce.model.Orders;
import com.idat.ecommerce.model.Product;
import com.idat.ecommerce.repository.AccountRepository;
import com.idat.ecommerce.repository.ProductRepository;
import com.idat.ecommerce.service.AccountService;
import com.idat.ecommerce.service.OrderService;
import com.idat.ecommerce.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Override
    public Orders saveOrder(OrderDto orderDto) {
        Orders saveOrder = new Orders();
        String uuid = UUID.randomUUID().toString();
        saveOrder.setOrderUuid(uuid);
        LocalDateTime createdDate = LocalDateTime.now();
        saveOrder.setCreatedDate(createdDate);
        Account account = orderDto.getAccount();
        saveOrder.setAccount(account);
        List<OrderDetails> orderDetailsList = orderDto.getOrderDetailsRegistrationList();
        saveOrder.setOrderDetailsList(orderDetailsList);

        List<OrderDetailsRegistration> orderDetailsRegistrationList = new ArrayList<>();

        if (orderDetailsList != null && !orderDetailsList.isEmpty()) {
            for (OrderDetails orderDetail : orderDetailsList) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                OrderDetailsRegistration orderDetailsRegistration = new OrderDetailsRegistration();
                String productUuid = orderDetail.getProduct().getId();

                Product product = productService.getProductByUuid(productUuid)
                        .orElseThrow(() -> new EntityNotFoundException("The account does not exist." + productUuid));

                orderDetailsRegistration.setProductName(product.getName());

                int quantity = orderDetail.getQuantity();
                orderDetailsRegistration.setQuantity(quantity);

                Double price = product.getPrice();
                orderDetailsRegistration.setPrice(price);

                double igv = 0.18*price;
                double amountTotal = calculateTotal(price, quantity);
                orderDetailsRegistration.setTotalAmount(amountTotal);


                orderDetailsRegistration.setTaxesAmount(0.0);

                double total = amountTotal +igv ;
                orderDetailsRegistration.setTotal(Double.valueOf(decimalFormat.format(total)));

                orderDetailsRegistrationList.add(orderDetailsRegistration);
            }

            List<OrderDetails> updatedOrderDetailsList = new ArrayList<>();
            for (OrderDetailsRegistration registration : orderDetailsRegistrationList) {

                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailUuid(UUID.randomUUID().toString());
                String productName = registration.getProductName();
                Product product = productService.getProduct(productName)
                        .orElseThrow(() -> new EntityNotFoundException("The account does not exist." + productName));
                orderDetails.setProduct(product);
                orderDetails.setQuantity(registration.getQuantity());
                orderDetails.setPrice(registration.getPrice());

                orderDetails.setTotal(registration.getTotal());
                updatedOrderDetailsList.add(orderDetails);
            }

            saveOrder.setOrderDetailsList(updatedOrderDetailsList);



        }

        return saveOrder;
    }



    private double calculateTotal(Double price, int quantity) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double amount = price * quantity;
        return Double.parseDouble(decimalFormat.format(amount));
    }
}
