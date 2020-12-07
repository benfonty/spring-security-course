package com.example.couponservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CouponMvcController {

    @Autowired
    private CouponRepository couponRepository;

    @RequestMapping(value = "/showCreateCoupon", method = RequestMethod.GET)
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @RequestMapping(value = "/saveCoupon", method = RequestMethod.POST)
    public String createCoupon(final Coupon coupon) {
        couponRepository.save(coupon);
        return "index";
    }

    @RequestMapping(value = "/showGetCoupon", method = RequestMethod.GET)
    public String showGetCoupon() {
        return "getCoupon";
    }

    @RequestMapping(value = "/showCouponResult", method = RequestMethod.GET)
    public ModelAndView showCouponResult(final String code) {
        final Coupon coupon = couponRepository.findByCode(code);
        return new ModelAndView("couponResult", Map.of("coupon", coupon));
    }
}


