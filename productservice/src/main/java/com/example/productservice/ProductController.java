package com.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestOperations restTemplate;

    @PostMapping
    public Product create(@RequestBody final Product product, @RequestParam(value = "withCoupon", required = true) String couponCode ) {
        final Coupon coupon = restTemplate.getForObject(MessageFormat.format("http://localhost:1111/coupons/{0}", couponCode), Coupon.class);
        final BigDecimal discount = Optional.ofNullable(coupon).map(Coupon::getDiscount).orElseGet(() -> new BigDecimal(0));
        return productRepository.save(product.withPrice(product.getPrice().subtract(discount)));
    }
}
