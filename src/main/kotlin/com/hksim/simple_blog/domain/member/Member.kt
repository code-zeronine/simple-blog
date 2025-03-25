package com.hksim.simple_blog.domain.member

import com.hksim.simple_blog.domain.AuditingEntity
import jakarta.persistence.*

@Entity
@Table(name = "Member")
class Member(
    email: String,
    password: String,
    role: Role
) : AuditingEntity() {
    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "password")
    var password: String = password
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: Role = role
        protected set

    override fun toString(): String {
        return "Member(email='$email', password='$password', role=$role)"
    }

}

enum class Role {
    ADMIN,
    USER
}