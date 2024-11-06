package com.alluz.blog.domain.account;

import com.alluz.blog.domain.AuditableEntity;
import com.alluz.blog.domain.comment.Comment;
import com.alluz.blog.domain.post.Blog;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Table(name = "accounts")
public class UserAccount extends AuditableEntity {

    private String email;

    private String password;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "locked_account")
    private boolean lockedAccount;

    @Column(name = "trusted_account")
    private boolean trustedAccount;

    private UserRole[] roles;

    @OneToMany(mappedBy="author")
    private Set<Blog> blogs;

    @OneToMany(mappedBy="user")
    private Set<Comment> comments;

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

    public boolean isLockedAccount() {
        return lockedAccount;
    }

    public void setLockedAccount(boolean lockedAccount) {
        this.lockedAccount = lockedAccount;
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

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public boolean isAuthor(){
        for (UserRole role : getRoles()) {
            if (role == UserRole.AUTHOR){
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(){
        for (UserRole role : getRoles()) {
            if (role == UserRole.ADMIN){
                return true;
            }
        }
        return false;
    }
}
