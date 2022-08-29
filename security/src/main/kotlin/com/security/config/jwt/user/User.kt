package com.security.config.jwt.user

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
open class User (
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var username: String? = null,
    var password: String? = null
) : Serializable