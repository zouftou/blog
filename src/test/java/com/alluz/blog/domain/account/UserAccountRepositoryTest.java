package com.alluz.blog.domain.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserAccountRepositoryTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    void testFindByEmail() {
        Optional<UserAccount> userAccount = userAccountRepository.findByEmail("admin@blog.com");
        assertTrue(userAccount.isPresent());
        assertEquals("Admin", userAccount.get().getDisplayName());
    }

    @Test
    void testFindAllByOrderById() {
        Page<UserAccount> userAccountPage = userAccountRepository.findAllByOrderById(PageRequest.of(0, 5));
        List<UserAccount> userAccounts = userAccountPage.getContent();
        assertNotNull(userAccounts);
        assertEquals(3,userAccounts.size());
    }
}