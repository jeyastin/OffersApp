package com.offers.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.offers.demo.entity.Mail;
import com.offers.demo.repository.MailRepository;
import com.offers.demo.transport.MailTO;

@Service
@ConfigurationPropertiesBinding
public class MailService {

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	MailRepository mailRepository;
	
	@Value("${spring.mail.username}")
	String mailFrom; 
	
	public MailTO sendMail(MailTO mailTO) {
		String text = null;
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(mailTO.getMailTo());
			if(StringUtils.isEmpty(mailTO.getMailFrom())){
				mailTO.setMailFrom(mailFrom);
			}
			msg.setFrom(mailTO.getMailFrom());
			msg.setSubject(mailTO.getOfferTO().getOfferName());
			text = "Offer Name: "+ mailTO.getOfferTO().getOfferName() + " Offer Address: " + mailTO.getOfferTO().getAddress() + " Offer Phone: "
					+ mailTO.getOfferTO().getPhone() + "";
			msg.setText(text);
			mailSender.send(msg);
		} catch (MailException e) {
			e.printStackTrace();
		}
		mailTO.setStatus("Mail Sent");
		trackMail(mailTO, text);
		return mailTO;
	}
	
	void trackMail(MailTO mailTO, String body){
		Mail mail = new Mail();
		BeanUtils.copyProperties(mailTO, mail);
		mail.setBody(body);
		mailRepository.save(mail);
	}

}
