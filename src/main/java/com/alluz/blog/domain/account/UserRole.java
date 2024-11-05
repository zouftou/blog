package com.alluz.blog.domain.account;

/**
 * Security role type for UserAccount.
 */
public enum UserRole {
    ADMIN,  // can manage user account, all posts
    AUTHOR, // can manage own posts
    USER   // can edit own comment, can edit own profile
    ;
}
