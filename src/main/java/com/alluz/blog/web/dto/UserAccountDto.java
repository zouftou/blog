package com.alluz.blog.web.dto;

import com.alluz.blog.domain.account.UserRole;

public class UserAccountDto {

    private Long id;

    private String email;

    private String password;

    private String displayName;

    private String imageUrl;

    private String website;

    private boolean accountLocked;

    private boolean trustedAccount;

    private UserRole[] roles;

    public boolean isAdmin(){
        for (UserRole role : getRoles()) {
            if (role == UserRole.ADMIN){
                return true;
            }
        }
        return false;
    }

    public boolean isAuthor(){
        for (UserRole role : getRoles()) {
            if (role == UserRole.AUTHOR){
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isTrustedAccount() {
        return trustedAccount;
    }

    public void setTrustedAccount(boolean trustedAccount) {
        this.trustedAccount = trustedAccount;
    }

    public UserRole[] getRoles() {
        return roles;
    }

    public void setRoles(UserRole[] roles) {
        this.roles = roles;
    }
}
