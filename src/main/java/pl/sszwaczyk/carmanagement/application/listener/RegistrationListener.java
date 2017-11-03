package pl.sszwaczyk.carmanagement.application.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.sszwaczyk.carmanagement.application.config.url.RegistrationURLs;
import pl.sszwaczyk.carmanagement.application.config.url.Server;
import pl.sszwaczyk.carmanagement.application.domain.entities.User;
import pl.sszwaczyk.carmanagement.application.domain.service.IUserService;
import pl.sszwaczyk.carmanagement.application.event.OnRegistrationCompleteEvent;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + RegistrationURLs.registrationConfirm + "?token=" + token;
//        String message = messages.getMessage("message.regSuccess", null, event.getLocale());
        String message = "Registration Success!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + Server.developServerURL + confirmationUrl);
        mailSender.send(email);
    }
}
