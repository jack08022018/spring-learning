package com.security.config.jwt.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        // Kiểm tra xem user có tồn tại trong database không?
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return CustomUserDetails(user)
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    fun loadUserById(id: Long): UserDetails {
        val user = userRepository.findById(id)
            .orElseThrow { UsernameNotFoundException("User not found with id : $id") }
        return CustomUserDetails(user)
    }
}