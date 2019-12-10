package com.artur.engineer.engine.managers;

import com.artur.engineer.engine.exceptions.ApiException;
import com.artur.engineer.engine.readers.UserReader;
import com.artur.engineer.entities.User;
import com.artur.engineer.payload.ApiResponse;
import com.artur.engineer.payload.publics.PasswordSetPayload;
import com.artur.engineer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.NotFoundException;
import java.util.*;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
@Component("PublicManager")
public class PublicManager {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public JavaMailSender emailSender;

    @Value("${app.front.url}")
    private String frontUrl;


    public void resetPassword(String email) throws MessagingException {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found")
        );


        String emailToken = Base64.getEncoder().encodeToString(email.getBytes());
        String token = UUID.randomUUID().toString() + emailToken;
        user.setPasswordResetToken(token);
        userRepository.save(user);

        MimeMessage message = this.emailSender.createMimeMessage();

        message.setSubject("Reset hasła");
        MimeMessageHelper helper = new MimeMessageHelper(message, true);;
        helper.setTo(email);
        String link = frontUrl+"password-reset/"+token;
        helper.setText("Kliknij w ponizszy link by zresetowac haslo <p><a href=\""+link+"\">Zresetuj</a></p>", true);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Reset hasła");
//        message.setText("Kliknij w link by zresetować hasło "+frontUrl+"password-reset/"+token);

        emailSender.send(message);
    }

    public void setPassword(PasswordSetPayload payload) {

        User user = userRepository.findByPasswordResetToken(payload.getToken()).orElseThrow(
                () -> new NotFoundException("Token not found")
        );

        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setPasswordResetToken("");
        userRepository.save(user);

    }

}
