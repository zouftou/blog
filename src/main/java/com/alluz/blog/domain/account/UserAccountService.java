package com.alluz.blog.domain.account;

import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public UserAccountDto getUserAccountByEmail(String email) {
        Optional<UserAccount> account = userAccountRepository.findByEmail(email);
        if(account.isPresent()){
            return modelMapper.map(account, UserAccountDto.class);
        }else {
            throw new ResourceNotFoundException(UserAccount.class.getName(),"email",email);
        }
    }

    public Page<UserAccountDto> getUserAccounts(Pageable pageable) {
        Page<UserAccount> userAccounts = userAccountRepository.findAllByOrderById(pageable);
        List<UserAccountDto> userAccountDtoList = userAccounts.stream().map(userAccount -> modelMapper.map(userAccount, UserAccountDto.class)).toList();
        return new PageImpl<>(userAccountDtoList);
    }

    public UserAccountDto saveUserAccount(UserAccountDto user) {
        UserAccount account = modelMapper.map(user, UserAccount.class);
        LOGGER.info("A new user is created (userId='{}') for '{}' with email '{}'.", account.getId(), account.getDisplayName(), account.getEmail());
        UserAccount savedAccount = userAccountRepository.save(account);
        return modelMapper.map(savedAccount, UserAccountDto.class);
    }

    public UserAccountDto getUserAccount(Long userId) {
        UserAccount account = userAccountRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(UserAccount.class.getName(),"userId",userId));
        return modelMapper.map(account, UserAccountDto.class);
    }

    public UserAccountDto updateUserAccount(Long userId, UserAccountDto userAccountDto) {
        UserAccount account = userAccountRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(UserAccount.class.getName(),"id",userId));
        account.setPassword(userAccountDto.getPassword());
        account.setEmail(userAccountDto.getEmail());
        account.setDisplayName(userAccountDto.getDisplayName());
        UserAccount savedAccount = userAccountRepository.save(account);
        return modelMapper.map(savedAccount, UserAccountDto.class);
    }

    public UserAccountDto addRole(Long userId, UserRole role) {
        UserAccount account =  userAccountRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(UserAccount.class.getName(),"userId",userId));
        Set<UserRole> roleSet = new HashSet<>(Arrays.asList(account.getRoles()));
        roleSet.add(role);
        account.setRoles(roleSet.toArray(new UserRole[0]));
        UserAccount savedAccount = userAccountRepository.save(account);
        return  modelMapper.map(savedAccount, UserAccountDto.class);
    }

    public UserAccountDto removeRole(Long userId, UserRole role) {
        UserAccount account = userAccountRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(UserAccount.class.getName(),"userId",userId));
        Set<UserRole> roleSet = new HashSet<>(Arrays.asList(account.getRoles()));
        roleSet.remove(role);
        account.setRoles(roleSet.toArray(new UserRole[0]));
        UserAccount savedAccount = userAccountRepository.save(account);
        return  modelMapper.map(savedAccount, UserAccountDto.class);
    }
}
