package com.alluz.blog.domain.account;

import com.zouftou.blog.web.dto.UserAccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @InjectMocks
    UserAccountService userAccountService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    void createUserAccount() {
        UserAccount account = new UserAccount();
        UserAccountDto accountDto = new UserAccountDto();

        when(modelMapper.map(any(UserAccountDto.class),eq(UserAccount.class))).thenReturn(account);
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(account);
        when(modelMapper.map(any(UserAccount.class),eq(UserAccountDto.class))).thenReturn(accountDto);

        UserAccountDto userAccountDto = userAccountService.createUserAccount(accountDto);

        assertThat(userAccountDto).isNotNull();
    }

    @Test
    void addRole() {
        UserAccount account = new UserAccount();
        account.setRoles(new UserRole[] {});
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.setRoles(new UserRole[] {UserRole.AUTHOR});
        accountDto.setId(1L);

        when(userAccountRepository.findById(any(Long.class))).thenReturn(Optional.of(account));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(account);
        when(modelMapper.map(any(UserAccount.class),eq(UserAccountDto.class))).thenReturn(accountDto);

        UserAccountDto userAccountDto = userAccountService.addRole(accountDto.getId(),UserRole.AUTHOR);

        assertThat(userAccountDto).isNotNull();
        UserRole[] roles = userAccountDto.getRoles();
        assertTrue(containsElement(roles, UserRole.AUTHOR));
    }

    @Test
    void removeRole() {
        UserAccount account = new UserAccount();
        account.setRoles(new UserRole[] {});
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.setRoles(new UserRole[] {});
        accountDto.setId(1L);

        when(userAccountRepository.findById(any(Long.class))).thenReturn(Optional.of(account));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(account);
        when(modelMapper.map(any(UserAccount.class),eq(UserAccountDto.class))).thenReturn(accountDto);

        UserAccountDto userAccountDto = userAccountService.addRole(accountDto.getId(),UserRole.AUTHOR);

        assertThat(userAccountDto).isNotNull();
        UserRole[] roles = userAccountDto.getRoles();
        assertFalse(containsElement(roles, UserRole.AUTHOR));
    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void getUserAccounts() {
    }

    @Test
    void getUserAccount() {
    }

    @Test
    void updateUserAccount() {
    }

    @Test
    void deleteUserAccount() {
    }

    public boolean containsElement(UserRole[] roles, UserRole element) {
        for (UserRole role : roles) {
            if (role == element){
                return true;
            }
        }
        return false;
    }
}