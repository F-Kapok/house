package com.fans.service.interfaces;

/**
 * @InterfaceName MailService
 * @Description:
 * @Author fan
 * @Date 2019-04-04 15:32
 * @Version 1.0
 **/
public interface MailService {
    void sendMail(String title, String url, String email);

    void registerNotify(String email);

    boolean enable(String key);
}
