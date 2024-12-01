package com.alluz.blog.domain.account;

import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserAccountService userAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentUser() {
        // Arrange
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("test@example.com");
        UserAccountDto userAccountDto = new UserAccountDto();
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userAccountRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userAccount));
        when(modelMapper.map(userAccount, UserAccountDto.class)).thenReturn(userAccountDto);

        // Act
        UserAccountDto result = userAccountService.getCurrentUser();

        // Assert
        assertNotNull(result);
        verify(userAccountRepository, times(1)).findByEmail("test@example.com");
        verify(modelMapper, times(1)).map(userAccount, UserAccountDto.class);
    }

    @Test
    void testGetCurrentUserThrowsWhenNotFound() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("notfound@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userAccountRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, userAccountService::getCurrentUser);
    }

    @Test
    void testCreateUserAccount() {
        // Arrange
        UserAccountDto userAccountDto = new UserAccountDto();
        UserAccount userAccount = new UserAccount();
        UserAccount savedAccount = new UserAccount();
        UserAccountDto savedDto = new UserAccountDto();
        when(modelMapper.map(userAccountDto, UserAccount.class)).thenReturn(userAccount);
        when(userAccountRepository.save(userAccount)).thenReturn(savedAccount);
        when(modelMapper.map(savedAccount, UserAccountDto.class)).thenReturn(savedDto);

        // Act
        UserAccountDto result = userAccountService.createUserAccount(userAccountDto);

        // Assert
        assertNotNull(result);
        verify(userAccountRepository, times(1)).save(userAccount);
        verify(modelMapper, times(2)).map(any(), any());
    }

    @Test
    void testGetUserAccountByEmail() {
        // Arrange
        String email = "test@example.com";
        UserAccount userAccount = new UserAccount();
        UserAccountDto userAccountDto = new UserAccountDto();
        when(userAccountRepository.findByEmail(email)).thenReturn(Optional.of(userAccount));
        when(modelMapper.map(userAccount, UserAccountDto.class)).thenReturn(userAccountDto);

        // Act
        UserAccountDto result = userAccountService.getUserAccountByEmail(email);

        // Assert
        assertNotNull(result);
        verify(userAccountRepository, times(1)).findByEmail(email);
        verify(modelMapper, times(1)).map(userAccount, UserAccountDto.class);
    }

    @Test
    void testGetUserAccountByEmailThrowsWhenNotFound() {
        // Arrange
        String email = "notfound@example.com";
        when(userAccountRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userAccountService.getUserAccountByEmail(email));
    }

    @Test
    void testGetUserAccounts() {
        // Arrange
        PageRequest pageable = PageRequest.of(0, 10);
        UserAccount userAccount = new UserAccount();
        UserAccountDto userAccountDto = new UserAccountDto();
        Page<UserAccount> userAccountPage = new PageImpl<>(List.of(userAccount));
        when(userAccountRepository.findAllByOrderById(pageable)).thenReturn(userAccountPage);
        when(modelMapper.map(userAccount, UserAccountDto.class)).thenReturn(userAccountDto);

        // Act
        Page<UserAccountDto> result = userAccountService.getUserAccounts(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(userAccountRepository, times(1)).findAllByOrderById(pageable);
        verify(modelMapper, times(1)).map(userAccount, UserAccountDto.class);
    }

    @Test
    void testUpdateUserAccount() {
        // Arrange
        Long userId = 1L;
        UserAccount existingAccount = new UserAccount();
        existingAccount.setEmail("old@example.com");
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setEmail("new@example.com");
        UserAccount updatedAccount = new UserAccount();
        updatedAccount.setEmail("new@example.com");
        when(userAccountRepository.findById(userId)).thenReturn(Optional.of(existingAccount));
        when(userAccountRepository.save(existingAccount)).thenReturn(updatedAccount);
        when(modelMapper.map(updatedAccount, UserAccountDto.class)).thenReturn(userAccountDto);

        // Act
        UserAccountDto result = userAccountService.updateUserAccount(userId, userAccountDto);

        // Assert
        assertNotNull(result);
        assertEquals("new@example.com", result.getEmail());
        verify(userAccountRepository, times(1)).findById(userId);
        verify(userAccountRepository, times(1)).save(existingAccount);
        verify(modelMapper, times(1)).map(updatedAccount, UserAccountDto.class);
    }

    @Test
    void testUpdateUserAccountThrowsWhenNotFound() {
        // Arrange
        Long userId = 1L;
        UserAccountDto userAccountDto = new UserAccountDto();
        when(userAccountRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userAccountService.updateUserAccount(userId, userAccountDto));
    }
}