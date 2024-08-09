package com.pos.system.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.ORDER_READ,
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.PRODUCT_READ,
            ApplicationUserPermission.PRODUCT_WRITE
    )),
    USER(Sets.newHashSet(
            ApplicationUserPermission.ORDER_READ,
            ApplicationUserPermission.ORDER_WRITE,
            ApplicationUserPermission.PRODUCT_READ
    ));

    private final Set<ApplicationUserPermission> applicationUserPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermissions) {
        this.applicationUserPermissions = applicationUserPermissions;
    }

    public Set<ApplicationUserPermission> getApplicationUserPermissions() {
        return applicationUserPermissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getApplicationUserPermissions().stream().map(permission ->
                new SimpleGrantedAuthority(permission.getPermission())
        ).collect(Collectors.toSet());
        permissions.add(
                new SimpleGrantedAuthority("ROLE_" + this.name())
        );
        return permissions;
    }
}
