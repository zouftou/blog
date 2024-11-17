package com.alluz.blog.web.rest.admin;

import com.alluz.blog.domain.account.UserAccountService;
import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class AdminUserAccountController {

    private final UserAccountService userAccountService;

    public AdminUserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(ApiUrls.URL_ADMIN_USERS)
    public ResponseEntity<Page<UserAccountDto>> getUserAccounts(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<UserAccountDto> userAccounts = userAccountService.getUserAccounts(pageable);
        return ResponseEntity.ok(userAccounts);
    }

    @RequestMapping(ApiUrls.URL_ADMIN_USERS_USER)
    public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("userId") Long userId) {
        UserAccountDto userAccount = userAccountService.getUserAccountById(userId);
        return ResponseEntity.ok(userAccount);
    }
}
