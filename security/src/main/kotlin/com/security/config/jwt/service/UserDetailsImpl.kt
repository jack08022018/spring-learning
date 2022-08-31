package com.security.config.jwt.service

import com.fasterxml.jackson.annotation.JsonIgnore
import com.security.config.jwt.user.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

internal class UserDetailsImpl(
    private val id: Long,
    private val username: String,

    @field:JsonIgnore
    private val password: String,

    private val authorities: Collection<GrantedAuthority>

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return setOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
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

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as UserDetailsImpl
        return id == user.id
    }

    companion object {
        private const val serialVersionUID = 1L
        fun build(user: UserEntity): UserDetailsImpl {
            val authorities: List<GrantedAuthority> = user.roles!!.stream()
                .map { role -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toList())
            return UserDetailsImpl(
                user.id!!,
                user.username!!,
                user.password!!,
                authorities
             )
        }
    }
}