package com.alluz.blog.web.rest.site;

import com.alluz.blog.domain.account.UserAccountService;
import com.alluz.blog.domain.comment.CommentService;
import com.alluz.blog.domain.comment.CommentStatus;
import com.alluz.blog.domain.post.BlogService;
import com.alluz.blog.web.rest.MessageSender;
import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.dto.ContactDto;
import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class SiteController {

    private final BlogService blogService;

    private final CommentService commentService;

    private final UserAccountService userAccountService;

    private final MessageSender messageSender;

    public SiteController(BlogService blogService, CommentService commentService, UserAccountService userAccountService, MessageSender messageSender) {
        this.blogService = blogService;
        this.commentService = commentService;
        this.userAccountService = userAccountService;
        this.messageSender = messageSender;
    }

    /**
     * Get current user info. If not authenticated, return 401.
     */
    @RequestMapping(ApiUrls.URL_SITE_CURRENT_USER)
    public ResponseEntity<UserAccountDto> getCurrentUserAccount() {
        UserAccountDto currentUser = userAccountService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

    /**
     * Returns signed-up user public profile info.
     */
    @RequestMapping(ApiUrls.URL_SITE_PROFILES_USER)
    public ResponseEntity<UserAccountDto> getUserProfile(@PathVariable("userId") Long userId) {
        UserAccountDto userAccount = userAccountService.getUserAccountById(userId);
        return ResponseEntity.ok(userAccount);
    }

    @RequestMapping(ApiUrls.URL_SITE_PROFILES_USER_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getUserApprovedComments(@PathVariable("userId") String userId, @PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<CommentDto> comments = commentService.getUserComments(userId, CommentStatus.APPROVED,pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_SITE_LATEST_BLOG)
    public ResponseEntity<BlogDto> getLatestBlog(){
        BlogDto blog = blogService.getLatestBlog();
        return ResponseEntity.ok(blog);
    }

    @RequestMapping(ApiUrls.URL_SITE_RECENT_BLOGS)
    public ResponseEntity<Page<BlogDto>> getRecentBlogs(@PageableDefault(size = 3, page = 0) Pageable pageable){
        Page<BlogDto> blogs = blogService.getBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    @RequestMapping(ApiUrls.URL_SITE_RECENT_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getRecentComments(@PageableDefault(size = 3, page = 0) Pageable pageable){
        Page<CommentDto> comments = commentService.getRecentComments(CommentStatus.APPROVED,pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_SITE_TAG_CLOUDS)
    public ResponseEntity<Set<String>> getTagCloud(@PageableDefault(size = 5, page = 0) Pageable pageable) {
        Page<BlogDto> blogs = blogService.getBlogs(pageable);
        Set<String> tagStrings = new HashSet<>();
        for (BlogDto blog : blogs) {
            tagStrings.addAll(
                    Arrays.stream(blog.getTags().split(","))
                            .map(String::trim)
                            .collect(Collectors.toSet())
            );
        }
        return ResponseEntity.ok(tagStrings);
    }

    @PostMapping(ApiUrls.URL_SITE_CONTACT)
    public ResponseEntity<String> submitContactMessage(@RequestBody ContactDto contactForm) {
        messageSender.notifyAdminForContactMessage(contactForm);
        return ResponseEntity.ok("");
    }
}
