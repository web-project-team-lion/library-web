package com.example.webproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/oauth")
public class LoginController {

    @Autowired
    KaKaoService ks;
    @Autowired
    NaverService ns;

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }
    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @GetMapping("/kakao")
    public String getKakao(@RequestParam String code, Model model) throws IOException {
        System.out.println("code = " + code);
        String access_token = ks.getKakaoAccessToken(code);
        Map<String, Object> userInfo = ks.getUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "index";
    }

    @GetMapping("/naver")
    public String getNaver(@RequestParam String code, Model model) throws IOException {
        System.out.println("code = " + code);
        SecureRandom random = new SecureRandom();
        String stateString = new BigInteger(130, random).toString(32);
        String access_token = ns.getNaverAccessToken(code, stateString);
        Map<String, Object> userInfo = ns.getNaverUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "index";
    }
}