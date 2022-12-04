package top.wuyongshi.webapi.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author 郑明亮
 * @Time 2017年3月27日 下午5:56:17
 * @Description <p>Email发送工具类  </P>
 * @version 1.0  
 */
public class EmailUtils {
	private static Logger log = LoggerFactory.getLogger(EmailUtils.class);
	
	/** 发送邮件 */
	public static boolean sendMail(String userEmail, String content, String title) {
		boolean flag = false;
		try {
			// 发送邮件;
			Properties props = new Properties();
			// 发送邮件的协议
			props.setProperty("mail.transport.protocol", "smtp");// TODO--smtp
			// 发送服务器的主机地址
			props.setProperty("mail.host", "hwsmtp.exmail.qq.com");// 邮件主机名 smtp.qq.com
			props.setProperty("mail.smtp.port", "465");
//			System.out.println("mail.host:"+ParameterSetting.SMTP_HOST_ADDRESS);
//			System.out.println("mail.smtp.port:"+ParameterSetting.SMTP_PORT);
			// 请求身份验证
			props.setProperty("mail.smtp.auth", "true");
			//使用SSL，企业邮箱必需！
			//开启安全协议
			MailSSLSocketFactory sf = null;
			          try {
			              sf = new MailSSLSocketFactory();
			              sf.setTrustAllHosts(true);
			          } catch (GeneralSecurityException e1) {
			              e1.printStackTrace();
			          }
			          props.put("mail.smtp.ssl.enable", "true");
			          props.put("mail.smtp.ssl.socketFactory", sf);
			Session session = Session.getDefaultInstance(props);
			 session.setDebug(true);//显示调试信息
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("service@wuyongshi.top"));// 发送人的号
			message.setRecipients(Message.RecipientType.TO, userEmail);
			message.setSubject(title);

			MimeBodyPart part = new MimeBodyPart();
			part.setContent(content, "text/html;charset=utf-8");
			MimeMultipart mpart = new MimeMultipart();
			mpart.addBodyPart(part);
			message.setContent(mpart);
			message.saveChanges();

			Transport ts = session.getTransport();
//			String email = ParameterSetting.SENDER_EMAIL.trim(),pwd = ParameterSetting.SENDER_EMAIL_PWD.trim();
				String email = "service@wuyongshi.top",pwd = "Zml1072307340";
			System.out.println("email:"+email+"pwd:"+pwd);
				ts.connect(email,pwd);// 邮箱账号密码
				ts.sendMessage(message, message.getAllRecipients());
				System.out.println("邮件发送成功");
				ts.close();
				flag = true;
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败");

		}
		return flag;
	}
	/**
	 * 发报错邮件和短信
	 * 
	 * @param userEmail
	 * @param errorTime  --1 接口报错; 0 跑批报错
	 * @param errorMessage 
	 * @param flag
	 *           
	 */
	/*public static void sendErrorMessage(String userEmail, String errorTime,
			String errorMessage, int flag) {

		*//** 判断是否发送邮件 *//*
		String messageValidity = ParameterSetting.EMAIL_VALIDITY;
		*//** 判断是否发送短信 *//*
		String smsValidity = ParameterSetting.SMS_VALIDITY;

		String[] userEmails = userEmail.split(";", -1);
		// 拿到管理员的手机号
		String sms_nums = ParameterSetting.ADMIN_TEL_NUM;
		System.out.println("管理员手机号："+sms_nums);
		String[] smsNumArray = sms_nums.split(";");
		
		//获取服务器的ip地址
		String serverIp = DataUtils.getLocalIpAddress();
		String serverName = "";
		try {
			serverName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String title = "接口报错邮件！";

		if (flag == 0) {
			errorTime = "跑批" + errorTime;
			title = "跑批报错邮件！";
		}else if (flag == 1) {
			errorTime = "后台" + errorTime;
			title = "后台报错邮件！";
		}

		String content = "你好，服务器（" + serverName + "）内网IP地址为" + serverIp +"公网ip为："+DataUtils.getWebIpAddress()+ "："
				+ errorTime + "报错\t\r\"" + errorMessage + "\"，请注意！<br/>"
				+ "此邮件是一封自动发送的邮件，请不要回复！";

		if (messageValidity.equals("Y")) {
			for (int i = 0; i < userEmails.length; i++) {
				sendMail(userEmails[i], content, title);
			}
		}
		
		if (smsValidity.equals("Y")) {
			System.out.println("发送短信");
			for (int j = 0; j < smsNumArray.length; j++) {
				//TODO 把发送短信停用了
//				SmsUtils.sendSMS(Constants.SMS_ERROR_REMIND);
			}

		}else {
			System.out.println("没有开启短信功能");
		}
	}*/
	
	
	/**
	 * @author 郑明亮
	 * @Time：2016年12月1日 下午2:21:33 TODO 当有错误信息时，发送邮件短信通知管理员
	 * @param e
	 */
	/*public static void sendErrorMessage(Exception e) {
		String userEmail = ParameterSetting.ADMIN_EMAIL;
		String errorNo = DateUtils.getNowDateStrByPattern("yyyy-MM-dd HH:mm:ss");
		try(ByteArrayOutputStream buf = new ByteArrayOutputStream();) {
			e.printStackTrace(new java.io.PrintWriter(buf, true));
			String errorMessage = buf.toString();
			log.error(errorMessage);
			sendErrorMessage(userEmail, errorNo, errorMessage, 0);
		} catch (Exception e2) {
			log.error("sendErrorMessage",e2);
		}
	}
	
	*//**
	 * @author 郑明亮
	 * @Email zhengmingliang911@gmail.com
	 * @Time 2017年3月28日 下午4:51:50
	 * @Description <p> 当有错误信息时，发送邮件短信通知管理员  </P>
	 * @param extraInfo 额外的说明
	 * @param e 堆栈中的错误信息
	 *//*
	public static void sendErrorMessage(String extraInfo,Exception e) {
		String userEmail = ParameterSetting.ADMIN_EMAIL;
		String errorNo = DateUtils.getNowDateStrByPattern("yyyy-MM-dd HH:mm:ss");
		try(ByteArrayOutputStream buf = new ByteArrayOutputStream();) {
			e.printStackTrace(new java.io.PrintWriter(buf, true));
			String errorMessage = buf.toString();
			log.error(extraInfo,errorMessage);
			sendErrorMessage(userEmail, errorNo, extraInfo+"\n"+errorMessage, 0);
		} catch (Exception e2) {
			log.error("sendErrorMessage",e2);
		}
	}*/
	
	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
		sendMail("1072307340@qq.com", "【开发者俱乐部】您好，开发者俱乐部向您致敬", "【开发者俱乐部】");
//		System.out.println(DataUtils.getLocalHostIP());
//		System.out.println(DataUtils.getWebIpAddress());
//		System.out.println(DataUtils.getLocalIpAddress());
	}
}
