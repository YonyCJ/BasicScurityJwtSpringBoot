package com.prapp.examplesecurityjwtbasic.security;

import com.prapp.examplesecurityjwtbasic.dao.entity.UserEntity;
import com.prapp.examplesecurityjwtbasic.dao.entity.UserProfileEntity;
import com.prapp.examplesecurityjwtbasic.dao.repository.UserProfileRepository;
import com.prapp.examplesecurityjwtbasic.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado."));

        List<UserProfileEntity> profileByUser = userProfileRepository.findByUserId(userEntity.getId());

        // Recolectamos todas las authorities de todos los perfiles del usuario
        Set<GrantedAuthority> authorities = profileByUser.stream()
                .map(userProfile -> userProfile.getProfile().getName()) // Asumiendo que getName() devuelve el nombre del perfil
                .map(profileName -> new SimpleGrantedAuthority("ROLE_" + profileName))
                .collect(Collectors.toSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );

    }
}
