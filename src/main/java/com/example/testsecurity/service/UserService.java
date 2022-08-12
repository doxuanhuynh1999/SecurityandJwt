package com.example.testsecurity.service;

import com.example.testsecurity.entity.User;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
    public List<User> getAll(){

        return userRepository.findAll();
    }
    public User add(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public boolean delete (Long id){
        userRepository.deleteById(id);
        return true;
    }
    public User update(User user, Long userId) {
        User tmplUser = userRepository.findById(userId).orElse(null);
        if (tmplUser != null) {
            return userRepository.save(tmplUser);
        }
        return null;
    }
    public UserDetails loadUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return new CustomUserDetails(user);
    }
}
