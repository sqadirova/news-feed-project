package com.feed.news.service;

import com.feed.news.entity.Role;
import com.feed.news.entity.db.XUser;
import com.feed.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<XUser> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<XUser> findUserByEmail(String user_email){
        return userRepository.findByEmail(user_email);
    }

    @Override
    public XUser saveUser(XUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getConfirm_password()));
        user.setActive(1);
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(String password,String confirm_password, Integer userId) {
        userRepository.updatePassword(password,confirm_password, userId);
    }

    public boolean deleteUser(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }



}
