package com.alluz.blog.web.rest.user;

import com.alluz.blog.domain.account.UserAccountService;
import com.alluz.blog.domain.account.UserRole;
import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PatchMapping("/user/users/{userId}")
    public ResponseEntity<Void> updateUserAccount(@PathVariable(name="userId") Long userId, @RequestBody Map<String, String> updateMap) {
        String command = updateMap.get("command");
        UserAccountDto user = getUserAccountById(userId);

        if ("lock".equals(command)) {
            user.setAccountLocked(true);
            userAccountService.saveUserAccount(user);
        } else if ("unlock".equals(command)) {
            user.setAccountLocked(false);
            userAccountService.saveUserAccount(user);
        } else if ("trust".equals(command)) {
            user.setTrustedAccount(true);
            userAccountService.saveUserAccount(user);
        } else if ("untrust".equals(command)) {
            user.setTrustedAccount(false);
            userAccountService.saveUserAccount(user);
        } else if ("addAuthorRole".equals(command)) {
            userAccountService.addRole(userId, UserRole.AUTHOR);
        } else if ("removeAuthorRole".equals(command)) {
            userAccountService.removeRole(userId, UserRole.AUTHOR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserAccountDto getUserAccountById(Long userId) {
        UserAccountDto user = userAccountService.getUserAccount(userId);
        if (user == null) {
            throw new ResourceNotFoundException("UserAccountDto","userId",userId);
        }
        return user;
    }
}
