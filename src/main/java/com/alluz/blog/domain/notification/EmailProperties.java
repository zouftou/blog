package com.alluz.blog.domain.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application.email")
public class EmailProperties {
	/**
	 * Name of administrator the notification or contact message will send to.
	 */
    private String adminName;

    /**
     * Email of administrator the notification or contact message will send to.
     */
    private String adminEmail;

    /**
     * Message sender name, like "Zouftou Blog".
     */
    private String systemName;

    /**
     * Message sender email.
     */
    private String systemEmail;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
