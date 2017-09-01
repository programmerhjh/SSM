package tool;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件功能类
 * Created by acer on 2017/8/1.
 */
public class SendEmail {

    public static final String HOST = "smtp.qq.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 587;
    public static final String FROM = "605594106@qq.com";//发件人的email
    public static final String PWD = "zfstauljvezgbfgi";//发件人邮箱授权密码

    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);//是否需要验证码
        props.put("mail.smtp.starttls.enable",true);//SSL加密
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.transport.protocol", PROTOCOL);

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    /**
     * 发送方法
     * @param toEmail
     * @param content
     * @time 2017年8月1日9:43:20
     */
    public static void send(String toEmail , String content) {
        Session session = getSession();
        try {
            System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO,address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport transport = session.getTransport();
            transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    //测试发送邮件
    public static void main(String[] args){
        SendEmail.send("605594106@qq.com","你好");
    }

}
