package com.alluz.blog.domain.account;

import com.alluz.blog.web.dto.UserAccountDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

    private final UserAccountRepository userAccountRepository;

    private final ModelMapper modelMapper;

    public UserAccountService(UserAccountRepository userAccountRepository, ModelMapper modelMapper) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
    }

    public UserAccountDto getCurrentUser() {
        return null;
    }

    public UserAccountDto getUserAccountById(Long userId) {
        return null;
    }
}
