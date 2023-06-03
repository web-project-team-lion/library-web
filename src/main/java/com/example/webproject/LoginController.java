package com.example.webproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    GoogleService gs;

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }
    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/home";
    }
    @GetMapping("/kakao")
    public String getKakao(@RequestParam String code, Model model, HttpSession session) throws IOException {
        System.out.println("code = " + code);
        String access_token = ks.getKakaoAccessToken(code);
        Map<String, Object> userInfo = ks.getUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        String nickname = (String) userInfo.get("nickname");
        System.out.println(nickname);
        session.setAttribute("nickname", nickname);
        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "redirect:/home";
    }

    @GetMapping("/naver")
    public String getNaver(@RequestParam String code, Model model, HttpSession session) throws IOException {
        System.out.println("code = " + code);
        String access_token = ns.getNaverAccessToken(code);
        Map<String, Object> userInfo = ns.getNaverUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        String nickname = (String) userInfo.get("nickname");
        System.out.println(nickname);
        session.setAttribute("nickname", nickname);
        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "redirect:/home";
    }

    @GetMapping("/google")
    public String getGoogle(@RequestParam String code, Model model, HttpSession session) throws IOException {
        System.out.println("code = " + code);
        String access_token = gs.getGoogleAccessToken(code);
        Map<String, Object> userInfo = gs.getGoogleUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        String nickname = (String) userInfo.get("nickname");
        System.out.println(nickname);
        session.setAttribute("nickname", nickname);
        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "redirect:/home";
    }
}