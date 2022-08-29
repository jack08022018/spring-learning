package com.security.config.jwt.user
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    var user: User? = null
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
        return setOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String? {
        return user!!.password
    }

    override fun getUsername(): String? {
        return user!!.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}