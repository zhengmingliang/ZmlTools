package top.wuyongshi.webapi.utils;

import com.google.common.io.Files;
import com.sun.mail.util.MailSSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wys.utils.FileUtils;
import top.wys.utils.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author 郑明亮
 * @version 1.0
 * @Time 2017年3月27日 下午5:56:17
 * @Description <p>Email发送工具类  </P>
 */
public class EmailUtils {
    private static Logger log = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * 发送邮件
     */
    public static boolean sendMail(String userEmail, String content, String title, String filePath) {
        boolean flag = false;
        try {
            // 发送邮件;
            Properties props = new Properties();
            // 发送邮件的协议
            props.setProperty("mail.transport.protocol", "smtp"); // TODO--smtp
            // 发送服务器的主机地址
            props.setProperty("mail.host", "hwsmtp.exmail.qq.com"); // 邮件主机名 smtp.qq.com
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
            String email = "service@wuyongshi.top", pwd = "xVZWd5D3CcN:dB9k";
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, pwd);
                }
            });
            session.setDebug(true); //显示调试信息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("service@wuyongshi.top")); // 发送人的号
            message.setRecipients(Message.RecipientType.TO, userEmail);
            message.setSubject(title);

            // 设置邮件正文
            MimeBodyPart part = new MimeBodyPart();
            part.setContent(content, "text/html;charset=utf-8");
            MimeMultipart mpart = new MimeMultipart();


            // 判断附件内容
            if (StringUtils.isNotEmpty(filePath)) {
                File file = new File(filePath);
                if (file.exists()) {
                    // 如果是文件夹，则遍历该文件夹下的文件（只列出第一级文件）
                    if (file.isDirectory()) {
                        File[] files = FileUtils.listFiles(file);
                        for (File fileItem : files) {
                            // 创建并添加附件
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            FileDataSource source = new FileDataSource(fileItem); // 附件文件路径
                            attachmentPart.setDataHandler(new DataHandler(source));
                            attachmentPart.setFileName(fileItem.getName()); // 附件显示的名称
                            mpart.addBodyPart(attachmentPart);
                        }
                    } else {
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        FileDataSource source = new FileDataSource(file); // 附件文件路径
                        attachmentPart.setDataHandler(new DataHandler(source));
                        attachmentPart.setFileName(file.getName()); // 附件显示的名称
                        mpart.addBodyPart(attachmentPart);
                    }
                }
            }

            mpart.addBodyPart(part);
            message.setContent(mpart);
            message.saveChanges();

            Transport ts = session.getTransport();
//			String email = ParameterSetting.SENDER_EMAIL.trim(),pwd = ParameterSetting.SENDER_EMAIL_PWD.trim();

//			System.out.println("email:"+email+"pwd:"+pwd);
            ts.connect(email, pwd); // 邮箱账号密码
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

    public static void sendMailWithFiles(String[] args) {
        final String from = "your_email@example.com";
        String to = "recipient_email@example.com";
        String host = "smtp.example.com";
        final String password = "your_email_password";

        // 设置系统属性和邮件会话
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // 或者其它端口，根据SMTP服务器配置
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密

        Session session = Session.getDefaultInstance(properties);

        try {
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("带有附件的邮件");
            message.setText("请查收附件");

            // 创建并添加附件
            MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(new File("path_to_your_attachment_file")); // 附件文件路径
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("attachment_filename"); // 附件显示的名称

            // 创建多重内容类型的消息
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);

            // 设置邮件正文
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("邮件正文内容");
            multipart.addBodyPart(textPart);

            // 将多重内容类型设置为邮件内容
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件已成功发送！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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

	*/

    /**
     * @param extraInfo 额外的说明
     * @param e         堆栈中的错误信息
     * @author 郑明亮
     * @Email zhengmingliang911@gmail.com
     * @Time 2017年3月28日 下午4:51:50
     * @Description <p> 当有错误信息时，发送邮件短信通知管理员  </P>
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
        sendMail("1072307340@qq.com", "【开发者俱乐部】您好，开发者俱乐部向您致敬", "【linux-redis安装包】",
                "/home/zml/下载/redis-stable.tar.gz");
		/*File[] files = FileUtils.listFiles("/opt/share/file/上线/长城资产/拆包");
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			sendMail("1072307340@qq.com", String.format("【长城资产】次为gateway的第%s个拆包文件", i+1), "【长城资产-gateway-拆包"+(i+1)
			+"】",file.getAbsolutePath());
		}*/


//		System.out.println(DataUtils.getLocalHostIP());
//		System.out.println(DataUtils.getWebIpAddress());
//		System.out.println(DataUtils.getLocalIpAddress());
    }
}
