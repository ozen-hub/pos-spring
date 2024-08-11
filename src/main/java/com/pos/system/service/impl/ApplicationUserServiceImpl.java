package com.pos.system.service.impl;

import com.pos.system.entity.ApplicationUser;
import com.pos.system.entity.UserRole;
import com.pos.system.exceptions.EntryNotFoundException;
import com.pos.system.repository.ApplicationUserRepo;
import com.pos.system.repository.UserRoleRepo;
import com.pos.system.security.SystemAdaptorUser;
import com.pos.system.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.pos.system.security.ApplicationUserRole.ADMIN;
import static com.pos.system.security.ApplicationUserRole.USER;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepo userRoleRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> selectedUserObject =
                userRepo.findByUsername(username);

        if (selectedUserObject.isEmpty()) {
            throw new RuntimeException(String.format("username not found %s", username));
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        for (UserRole ur : selectedUserObject.get().getUserRoles()) {
            if (ur.getRole().equals("ADMIN")) {
                grantedAuthorities.addAll(ADMIN.grantedAuthorities());
            }
            if (ur.getRole().equals("USER")) {
                grantedAuthorities.addAll(USER.grantedAuthorities());
            }
        }
        return new SystemAdaptorUser(
                selectedUserObject.get().getUsername(),
                selectedUserObject.get().getPassword(),
                selectedUserObject.get().isAccountNonExpired(),
                selectedUserObject.get().isCredentialsNonExpired(),
                selectedUserObject.get().isAccountNonLocked(),
                selectedUserObject.get().isEnabled(),
                grantedAuthorities
        );
    }

    @Override
    public void initializeUser() {
        Optional<ApplicationUser> selectedUserData = userRepo.findByUsername("hasikasandaruwan.info@gmail.com");
        if (selectedUserData.isPresent()) {
            //throw new DuplicateEntryException("");
            return;
        }
        Optional<UserRole> selectedRoleData = userRoleRepo.findByRole("ADMIN");
        if (selectedRoleData.isEmpty()) {
            throw new EntryNotFoundException("Role not found");
        }
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(selectedRoleData.get());

        userRepo.save(ApplicationUser.builder()
                .userId(UUID.randomUUID().toString())
                .fullName("Hasika Sandaruwan")
                .userRoles(userRoles)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .username("hasikasandaruwan.info@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build());

    }
}
