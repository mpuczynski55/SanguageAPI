package jpwp.jpwpproject.service;

import jpwp.jpwpproject.exception.exceptions.EmailSendException;
import jpwp.jpwpproject.util.EmailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    @Async
    public void send(String to, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject("Sanguage - account confirmation");
            helper.setFrom("sanguagejpwp@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException m) {
            throw new EmailSendException("failed to send mail", m);
        }
    }

    public String buildConfirmAccountEmail(String link) {
        Context context = new Context();
        context.setVariable("link", link);
        final String htmlContent = templateEngine.process("confirmAccount.html", context);
        return htmlContent;
    }
}
