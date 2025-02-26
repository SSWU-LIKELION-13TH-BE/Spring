package com.likelion.session.service.user;

import com.likelion.session.entity.user.User;
import com.likelion.session.repository.user.UserRepository;
import com.likelion.session.dto.user.request.UserLoginRequestDto;
import com.likelion.session.dto.user.request.UserSignupRequestDto;
import com.likelion.session.dto.user.response.UserLoginResponseDto;
import com.likelion.session.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔑 회원가입
    public void signup(UserSignupRequestDto requestDto) {
        User user = new User();
        user.setUserId(requestDto.getUserId());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setName(requestDto.getName());
        user.setProfileImage(requestDto.getProfileImage());

        userRepository.save(user);
    }

    // 🔐 로그인
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + requestDto.getUserId()));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtTokenProvider.createToken(user.getUserId());

        return new UserLoginResponseDto(user.getUserId(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
