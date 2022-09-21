package com.citi.ver1.BL;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.citi.ver1.dto.Stock;
import com.citi.ver1.dto.User;
import com.citi.ver1.repository.Login_Repo;

class stock {
	String company;
	double percDiff;
	stock(String company, double percDiff) {
		this.company = company;
		this.percDiff = percDiff;
	}
}
@Service
public class SendMailService {

	@Scheduled(initialDelay = 1000, fixedRate = 30000)
	public void scheduler() {
		sendEmail();
	}
	
	
	@Autowired
	Login_Repo loginRepo;
	
	@Autowired
	ProcessStocks processStocks;

	@Async
	void sendEmail() {
		stock s = sendUpdate();
		if(s != null) {
			List<User> users = loginRepo.getAllUsers();

		for(User u: users) {
				sendEmail(u, s.company, s.percDiff);
			}
		}
    }

	private stock sendUpdate() {
		Stock s = processStocks.sendTopStocks().get(0);
		if(s.getPercDiff().compareTo(new BigDecimal(20))>0) {
			return new stock(s.getCompanyName(), s.getPercDiff().doubleValue());
		}
		return null;
	}
	void sendEmail(User u, String company, double percDiff)
	{
		String from = "arbitragerecommender@gmail.com";
		String to = u.getEmail();
		String host = "smtp.gmail.com";
		String port = "587";	
		String user = "arbitragerecommender@gmail.com";
		String password = "omwoxxsitrhcthgy";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.smtp.user", user);
		properties.setProperty("mail.smtp.password", password);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from, ""));
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
			message.setSubject("BSE/NSE Arbitrage Recommendation Update");
			message.setText("Hello User, there exists an Arbitrage opportunity of " + percDiff + "% for the stocks of " + company + "." + "\nLogin to Arbitrage Recommendation System to see more details. \n\n\nThank You!\nBSE/NSE Arbitrage Recommendation System\nCitiBridge Group 8");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
	}
}