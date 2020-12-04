package com.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestOperations restTemplate;

    @Autowired
    BasicAuthentTransferService basicAuthentService;

    @PostMapping
    public Product create(@RequestBody final Product product, @RequestParam(value = "withCoupon", required = true) String couponCode ) {
        final HttpEntity<String> requestEntity = new HttpEntity<>(basicAuthentService.buildBAsicAuthentHeadersFromSecurityContext());
        ResponseEntity<Coupon> response = restTemplate.exchange(MessageFormat.format("http://localhost:1111/coupons/{0}", couponCode), HttpMethod.GET, requestEntity, Coupon.class);
        final BigDecimal discount = Optional.ofNullable(response.getBody()).map(Coupon::getDiscount).orElseGet(() -> new BigDecimal(0));
        return productRepository.save(product.withPrice(product.getPrice().subtract(discount)));
    }

    @GetMapping("/{id}")
    public Product get(final @PathVariable("id") Long id) {
        return productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
