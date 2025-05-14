package com.example.librarymanagementsystem.config.auditing;

import com.example.librarymanagementsystem.config.security.UserPrinciple;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Optional.ofNullable(userPrinciple.getUsername());
    }
}
