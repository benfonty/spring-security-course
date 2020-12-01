package com.example.couponservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponRepository couponRepo;

    @PostMapping
    public Coupon create( @RequestBody final Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @GetMapping("/{code}")
    public Coupon get(@PathVariable("code") String code) {
        return couponRepo.findByCode(code);
    }
}
