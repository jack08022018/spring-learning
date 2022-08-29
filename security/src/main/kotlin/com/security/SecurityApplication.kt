package com.security

import com.security.config.jwt.user.User
import com.security.config.jwt.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class SecurityApplication : CommandLineRunner {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun run(vararg args: String?) {
        val user = User()
        user.username = "loda"
        user.password = passwordEncoder.encode("loda")
        userRepository.save(user)
    }
}

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}
