package com.gcm.songs.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@org.springframework.stereotype.Service
public class Service {

	// public String sendMail(String content) throws Exception {
	// ZCMailContent mailContent = ZCMailContent.getInstance();
	// //Get a ZCMailContent instance
	// ArrayList toMailList = new ArrayList();
	// //Add the recipient email addresses as an array list
	// toMailList.add("gloriouscrownministriesindia@gmail.com");
	// ArrayList ccMailList = new ArrayList<>();
	// //Add the email addresses to CC as an array list
	// ccMailList.add("praisonsolomon.v@gmail.com");
	// mailContent.setFromEmail("gloriouscrownministriesindia@gmail.com");
	// //Set the sender's email address
	// mailContent.setToEmailList(toMailList); //Pass the recipient array list
	// mailContent.setCcEmailList(ccMailList); //Pass the CC array list
	// mailContent.setSubject("Greetings from GCM!"); //Set the email's subject
	// mailContent.setContent(content);
	// //Set the email's body as an HTML content
	// //Pass the email attachments array list
	// ZCMail.getInstance().sendMail(mailContent);
	// //Send emails using the mailContent object
	// System.out.println("Mail sent successfully!");
	// return "Mail sent successfully!";
	// }

	@Autowired
	private JavaMailSender javaMailSender;

	// public void sendMail(String feedbackText) throws MessagingException {
	// MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	// MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	// helper.setSubject("Feedback from user - GCM Songs Sheets");
	// helper.setFrom("gloriouscrownministriesindia@gmail.com");
	// String htmlContent = "<!DOCTYPE html>\n" +
	// "<html lang=\"en\">\n" +
	// "<head>\n" +
	// " <meta charset=\"UTF-8\">\n" +
	// " <meta name=\"viewport\" content=\"width=device-width,
	// initial-scale=1.0\">\n" +
	// " <title>Contact Us Confirmation</title>\n" +
	// " <style>\n" +
	// " body {\n" +
	// " font-family: Arial, sans-serif;\n" +
	// " background-color: #f4f4f4;\n" +
	// " margin: 0;\n" +
	// " padding: 0;\n" +
	// " color: #333;\n" +
	// " }\n" +
	// " .email-container {\n" +
	// " width: 100%;\n" +
	// " padding: 20px;\n" +
	// " background-color: #ffffff;\n" +
	// " box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n" +
	// " margin: 0 auto;\n" +
	// " max-width: 600px;\n" +
	// " border-radius: 8px;\n" +
	// " }\n" +
	// " .email-header {\n" +
	// " background: linear-gradient(90deg, #007BFF, #00C9FF);\n" +
	// " padding: 20px;\n" +
	// " text-align: center;\n" +
	// " color: white;\n" +
	// " border-radius: 8px 8px 0 0;\n" +
	// " }\n" +
	// " .email-header h1 {\n" +
	// " margin: 0;\n" +
	// " font-size: 24px;\n" +
	// " }\n" +
	// " .email-body {\n" +
	// " padding: 20px;\n" +
	// " line-height: 1.6;\n" +
	// " }\n" +
	// " .email-body h2 {\n" +
	// " color: #007BFF;\n" +
	// " }\n" +
	// " .email-footer {\n" +
	// " margin-top: 20px;\n" +
	// " text-align: center;\n" +
	// " font-size: 14px;\n" +
	// " color: #666;\n" +
	// " }\n" +
	// " .email-footer a {\n" +
	// " color: #007BFF;\n" +
	// " text-decoration: none;\n" +
	// " }\n" +
	// " .button {\n" +
	// " display: inline-block;\n" +
	// " background: linear-gradient(90deg, #007BFF, #00C9FF);\n" +
	// " color: white;\n" + /* Changed font color */
	// " padding: 10px 20px;\n" +
	// " text-decoration: none;\n" +
	// " border-radius: 5px;\n" +
	// " font-size: 16px;\n" +
	// " font-family: 'Verdana', sans-serif;\n" + /* Changed font family */
	// " font-weight: bold;\n" + /* Made text bold */
	// " margin-top: 20px;\n" +
	// " }\n" +
	// " </style>\n" +
	// "</head>\n" +
	// "<body>\n" +
	// " <div class=\"email-container\">\n" +
	// " <div class=\"email-header\">\n" +
	// " <h1>Feedback from song sheet user!</h1>\n" +
	// " </div>\n" +
	// " <div class=\"email-body\">\n" +
	// " <p>{{feedbackText}}</p>\n" +
	// " <a href=\"https://gcmsongsheets.web.app\" class=\"button\">GCM Song
	// sheets</a>\n" +
	// " </div>\n" +
	// " <div class=\"email-footer\">\n" +
	// " <p>&copy; 2025 Glorious Crown Ministries India</p>\n" +
	// " </div>\n" +
	// " </div>\n" +
	// "</body>\n" +
	// "</html>\n";

	// htmlContent = htmlContent.replace("{{feedbackText}}", feedbackText);
	// helper.setText(htmlContent, true);
	// this.javaMailSender.send(mimeMessage);
	// System.out.println("Mail sent to succesfully! - from Service");
	// }

	public void sendMail(String name, String emailId, String feedbackText) throws MessagingException {

		/*
		 * =========================================
		 * ADMIN FEEDBACK MAIL
		 * =========================================
		 */

		MimeMessage adminMessage = this.javaMailSender.createMimeMessage();

		MimeMessageHelper adminHelper = new MimeMessageHelper(adminMessage, true, "UTF-8");

		adminHelper.setSubject("Feedback from user - GCM Songs Sheets");

		adminHelper.setFrom("gloriouscrownministriesindia@gmail.com");

		adminHelper.setTo("gloriouscrownministriesindia@gmail.com");

		adminHelper.setCc(new String[] {
				"praisonsolomon.v@gmail.com",
				"victorjebaraj.d2011@gmail.com"
		});

		String adminHtml = "<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<style>" +

				"body {" +
				"font-family: Arial, sans-serif;" +
				"background-color: #f4f4f4;" +
				"margin: 0;" +
				"padding: 20px;" +
				"}" +

				".email-container {" +
				"max-width: 700px;" +
				"margin: auto;" +
				"background: #ffffff;" +
				"border-radius: 8px;" +
				"overflow: hidden;" +
				"box-shadow: 0 4px 8px rgba(0,0,0,0.1);" +
				"}" +

				".header {" +
				"background: linear-gradient(90deg, #007BFF, #00C9FF);" +
				"padding: 24px;" +
				"text-align: center;" +
				"color: white;" +
				"font-size: 24px;" +
				"font-weight: bold;" +
				"}" +

				".content {" +
				"padding: 30px;" +
				"font-size: 15px;" +
				"line-height: 1.6;" +
				"color: #333;" +
				"}" +

				".content h2 {" +
				"font-size: 22px;" +
				"margin-bottom: 20px;" +
				"}" +

				".label {" +
				"font-weight: bold;" +
				"}" +

				".button {" +
				"display: inline-block;" +
				"margin-top: 20px;" +
				"padding: 12px 22px;" +
				"background: linear-gradient(90deg, #007BFF, #00C9FF);" +
				"color: white !important;" +
				"text-decoration: none;" +
				"border-radius: 6px;" +
				"font-size: 15px;" +
				"font-weight: bold;" +
				"}" +

				".footer {" +
				"text-align: center;" +
				"padding: 20px;" +
				"font-size: 13px;" +
				"color: #777;" +
				"}" +

				"</style>" +
				"</head>" +

				"<body>" +

				"<div class='email-container'>" +

				"<div class='header'>" +
				"GCM Song Sheets User Feedback" +
				"</div>" +

				"<div class='content'>" +

				"<h2>Feedback Details</h2>" +

				"<p><span class='label'>Name:</span> "
				+ name + "</p>" +

				"<p><span class='label'>Email ID:</span> "
				+ emailId + "</p>" +

				"<p><span class='label'>Feedback:</span></p>" +

				"<p>" + feedbackText + "</p>" +

				"<a href='https://gcmsongsheets.web.app' class='button'>" +
				"GCM Song Sheets" +
				"</a>" +

				"</div>" +

				"<div class='footer'>" +
				"&copy; 2026 Glorious Crown Ministries India" +
				"</div>" +

				"</div>" +

				"</body>" +
				"</html>";

		adminHelper.setText(adminHtml, true);

		this.javaMailSender.send(adminMessage);

		/*
		 * =========================================
		 * USER CONFIRMATION MAIL
		 * =========================================
		 */

		MimeMessage userMessage = this.javaMailSender.createMimeMessage();

		MimeMessageHelper userHelper = new MimeMessageHelper(userMessage, true, "UTF-8");

		userHelper.setSubject("Thank You for Contacting GCM Song Sheets");

		userHelper.setFrom("gloriouscrownministriesindia@gmail.com");

		userHelper.setTo(emailId);

		String userHtml = "<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<style>" +

				"body {" +
				"font-family: Arial, sans-serif;" +
				"background-color: #f4f4f4;" +
				"margin: 0;" +
				"padding: 20px;" +
				"}" +

				".email-container {" +
				"max-width: 700px;" +
				"margin: auto;" +
				"background: #ffffff;" +
				"border-radius: 8px;" +
				"overflow: hidden;" +
				"box-shadow: 0 4px 8px rgba(0,0,0,0.1);" +
				"}" +

				".header {" +
				"background: linear-gradient(90deg, #007BFF, #00C9FF);" +
				"padding: 24px;" +
				"text-align: center;" +
				"color: white;" +
				"font-size: 24px;" +
				"font-weight: bold;" +
				"}" +

				".content {" +
				"padding: 30px;" +
				"font-size: 15px;" +
				"line-height: 1.6;" +
				"color: #333;" +
				"}" +

				".content h2 {" +
				"font-size: 22px;" +
				"margin-bottom: 20px;" +
				"}" +

				".button {" +
				"display: inline-block;" +
				"margin-top: 20px;" +
				"padding: 12px 22px;" +
				"background: linear-gradient(90deg, #007BFF, #00C9FF);" +
				"color: white !important;" +
				"text-decoration: none;" +
				"border-radius: 6px;" +
				"font-size: 15px;" +
				"font-weight: bold;" +
				"}" +

				".footer {" +
				"text-align: center;" +
				"padding: 20px;" +
				"font-size: 13px;" +
				"color: #777;" +
				"}" +

				"</style>" +
				"</head>" +

				"<body>" +

				"<div class='email-container'>" +

				"<div class='header'>" +
				"Thank You for Contacting Us!" +
				"</div>" +

				"<div class='content'>" +

				"<h2>Dear " + name + ",</h2>" +

				"<p>" +
				"Thank you for reaching out to us. " +
				"We have received your feedback successfully." +
				"</p>" +

				"<p>" +
				"Our team will review your feedback and get back to you if needed." +
				"</p>" +

				"<p>" +
				"God bless you!" +
				"</p>" +

				"<a href='https://gcmsongsheets.web.app' class='button'>" +
				"Visit Our Website" +
				"</a>" +

				"</div>" +

				"<div class='footer'>" +
				"&copy; 2026 Glorious Crown Ministries India. All rights reserved." +
				"</div>" +

				"</div>" +

				"</body>" +
				"</html>";

		userHelper.setText(userHtml, true);

		this.javaMailSender.send(userMessage);

		System.out.println("Both mails sent successfully!");
	}
}
