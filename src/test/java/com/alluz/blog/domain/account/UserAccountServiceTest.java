package com.alluz.blog.domain.account;

import com.alluz.blog.web.dto.UserAccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        UserAccount account1 = new UserAccount();
        UserAccountDto accountDto1 = new UserAccountDto();
        UserAccount account2 = new UserAccount();

        List<UserAccount> userAccountList = new ArrayList<>();
        userAccountList.add(account1);
        userAccountList.add(account2);

        when(userAccountRepository.findAllByOrderById(any(Pageable.class))).thenReturn(new PageImpl<>(userAccountList));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(account1);
        when(modelMapper.map(any(UserAccount.class),eq(UserAccountDto.class))).thenReturn(accountDto1);

        Page<UserAccount> userAccountPage = userAccountRepository.findAllByOrderById(PageRequest.of(0,5));
        assertNotNull(userAccountPage.getContent());
        assertEquals(2,userAccountPage.getContent().size());
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