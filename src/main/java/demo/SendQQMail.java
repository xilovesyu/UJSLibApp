package demo;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.internet.*;
public class SendQQMail {
		private String username;
		private String password;
		private String subject;
		private String text;
		private String sendto;
		
		public boolean send() {
		  boolean flag = true;
		  
		  //建立邮件会话
		  Properties pro = new Properties();
		  //debug
		  pro.setProperty("mail.debug", "true");
		  pro.put("mail.smtp.host","smtp.qq.com");//存储发送邮件的服务器
		  pro.put("mail.smtp.auth","true");  //通过服务器验证
		  
		  ///qq 必须开启 ssl
		  MailSSLSocketFactory sf=null;
		  try {
			  sf = new MailSSLSocketFactory();
		  } catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		  }
		  sf.setTrustAllHosts(true);
		  pro.put("mail.smtp.ssl.enable", "true");
		  pro.put("mail.smtp.ssl.socketFactory", sf);
		  
		  Session s =Session.getInstance(pro); //根据属性新建一个邮件会话
		  
		  //由邮件会话新建一个消息对象
		  MimeMessage message = new MimeMessage(s);
		  
		  //设置邮件
		  InternetAddress fromAddr = null;
		  InternetAddress toAddr = null;
		  
		  try {
		   fromAddr = new InternetAddress(username);   //邮件发送地址
		   message.setFrom(fromAddr);         //设置发送地址
		   
		   toAddr = new InternetAddress(sendto);       //邮件接收地址
		   message.setRecipient(Message.RecipientType.TO, toAddr);  //设置接收地址
		   
		   message.setSubject(subject);   //设置邮件标题
		   message.setText(text);   //设置邮件正文
		   message.setSentDate(new Date()); //设置邮件日期
		   
		   message.saveChanges();    //保存邮件更改信息

		   Transport transport = s.getTransport("smtp");
		   transport.connect("smtp.qq.com", username, password); //服务器地址，邮箱账号，邮箱密码
		   transport.sendMessage(message, message.getAllRecipients());   //发送邮件
		   transport.close();//关闭
		   } catch (Exception e)  {
		   e.printStackTrace();
		   flag = false;//发送失败
		  }
		  return flag;
		 } 
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public void setTo(String sendto) {
			this.sendto = sendto;
		}
		
		public void setSubject(String subject) {
			this.subject = subject;
		}
		
		public void setText(String text) {
			this.text = text;
		}
		public static void main(String[] args) {
			
		}
	}

