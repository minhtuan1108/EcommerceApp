package com.learn.ecommerce.Service;

import com.learn.ecommerce.Entity.User;
import com.learn.ecommerce.Repository.UserRepository;
import com.learn.ecommerce.Request.ChangePasswordRequest;
import com.learn.ecommerce.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<Map<String,String>> changePassword(ChangePasswordRequest request) throws UnsupportedEncodingException {

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        byte[] decode = Base64.getDecoder().decode(request.getCode());
        String email = new String(decode, "UTF-8");
        try{
            User user = userRepository.findByEmail(email).orElseThrow();
            System.out.println(user.getEmail());
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            Map<String,String> response = new HashMap<>();
            response.put("message","Thay đổi password thành công");
            repository.save(user);
            return ResponseEntity.ok().body(response);
        }
        catch(Exception e){
            Map<String,String> response = new HashMap<>();
            response.put("message","Code không đúng");
            return ResponseEntity.badRequest().body(response);
        }
    }
    public ResponseEntity<Map<String,String>> forgotPassword(String email) throws NoSuchAlgorithmException {

        try{
            User user = repository.findByEmail(email).orElseThrow();
            String link = emailService.generateLinkChangePassword(user);
            Map<String,String> reponse = new HashMap<>();
            reponse.put("message","Email đã được gửi");
            var subject = "Thay đổi mật khẩu";
            var text = "Nhấn vào link sau để được liên kết đến nơi thay đổi password  "+link;
            emailService.sendSimpleEmail(email,subject,text);
            return ResponseEntity.ok().body(reponse);
        }
        catch (NoSuchElementException e)
        {
            Map<String,String> reponse = new HashMap<>();
            reponse.put("message","Email chưa được đăng ký");
           return ResponseEntity.badRequest().body(reponse);
        }



    }

}
