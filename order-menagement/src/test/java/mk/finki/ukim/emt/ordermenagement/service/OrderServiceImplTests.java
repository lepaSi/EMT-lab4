package mk.finki.ukim.emt.ordermenagement.service;

import mk.finki.ukim.emt.ordermenagement.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermenagement.domain.model.Order;
import mk.finki.ukim.emt.ordermenagement.domain.model.OrderId;
import mk.finki.ukim.emt.ordermenagement.domain.valueobjects.Product;
import mk.finki.ukim.emt.ordermenagement.domain.valueobjects.ProductId;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderForm;
import mk.finki.ukim.emt.ordermenagement.service.forms.OrderItemForm;
import mk.finki.ukim.emt.ordermenagement.xport.client.ProductClient;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;

    private static Product newProduct(String name, Money cenaproiz){
        Product p = new Product(ProductId.randomId(ProductId.class),name,cenaproiz,0);
        return p;
    }

    @Test
    public void testPlaceOrder(){
        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(newProduct("Analgin",Money.valueOd(Currency.MKD,200)));
        oi1.setKolicinapn(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(newProduct("Paracetamol",Money.valueOd(Currency.MKD,500)));
        oi2.setKolicinapn(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.vkupnoc(),Money.valueOd(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData(){
        List<Product> productList = productClient.findAll();
        Product p1 = productList.get(0);
        Product p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(p1);
        oi1.setKolicinapn(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(p2);
        oi2.setKolicinapn(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getCenaproiz().multiply(oi1.getKolicinapn()).add(p2.getCenaproiz().multiply(oi2.getKolicinapn()));
        Assertions.assertEquals(newOrder.vkupnoc(),outMoney);

    }
}
