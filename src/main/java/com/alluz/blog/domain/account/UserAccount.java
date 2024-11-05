package com.alluz.blog.domain.account;

import com.alluz.blog.domain.AuditableEntity;
import com.alluz.blog.domain.comment.Comment;
import com.alluz.blog.domain.post.Blog;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Table(name = "accounts")
public class UserAccount extends AuditableEntity {

    private String email;

    private String password;

    private UserRole[] roles;

    @OneToMany(mappedBy="author")
    private Set<Blog> blogs;

    @OneToMany(mappedBy="user")
    private Set<Comment> comments;

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
}
