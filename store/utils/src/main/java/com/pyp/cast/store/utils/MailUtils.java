package com.pyp.cast.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	/*第一个参数：邮件地址
	 *第二个参数：邮件信息
	 */
	public static void sendMail(String email,String oid)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		//设置发送的协议
		//props.setProperty("mail.transport.protocol", "SMTP");

		//设置发送邮件的服务器
		//props.setProperty("mail.host", "smtp.126.com");
		//props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码
				return new PasswordAuthentication("admin@store.com", "admin");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		//设置发送者
		message.setFrom(new InternetAddress("admin@store.com"));

		//设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//设置邮件主题
		message.setSubject("用户签收购物包裹");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		String url="http://172.17.7.229:8080/store_web/adminOrder/updateOrderStatuByOid1.do?statu=3&oid="+oid;
		String content="<h1>请签收您的包裹!签收请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		//设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
	//public static void main(String[] args) throws AddressException, MessagingException {
	//	MailUtils.sendMail("zhangsan@store.com", "1234567890");
	//	System.out.println("OK");
	//}
}
