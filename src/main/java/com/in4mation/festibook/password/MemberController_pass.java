package com.in4mation.festibook.password;

import com.in4mation.festibook.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@Controller
public class MemberController_pass {

    private final MemberService_pass memberServicePass;

    public MemberController_pass(MemberService_pass memberServicePass) {
        this.memberServicePass = memberServicePass;
    }

    @GetMapping("/find_pass")
    public String findPass() {
        return "find_pass";
    }

    @Autowired
    private EmailService emailService;

    @PostMapping("/generateCode")
    @ResponseBody
    public String generateCode(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");
        int verificationCode = new Random().nextInt(900000) + 100000; // 6자리 랜덤 숫자 생성

        memberServicePass.updateCode(email, verificationCode);

        // 이메일로 인증번호 보내기
        emailService.sendSimpleMessage(email,
                "Festibook 비밀번호 인증코드입니다.",
                "회원님의 인증번호는 :  " + verificationCode + "  입니다.");

        return "인증번호가 회원님의 이메일로 발송되었습니다";
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");
        int code = (int) payload.get("code");
        return memberServicePass.verifyCodeAndGetPassword(email, code);
    }

}
