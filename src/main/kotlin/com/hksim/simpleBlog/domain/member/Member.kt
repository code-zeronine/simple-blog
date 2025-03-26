package com.hksim.simpleBlog.domain.member

import com.hksim.simpleBlog.domain.AuditingEntity
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

    companion object {
        fun createFakeMember(memberId: Long): Member {
            val member = Member("", "", Role.USER)
            member.id = memberId
            return member
        }
    }
}

fun Member.toDto(): MemberRes {
   return MemberRes(
       id = this.id!!,
       email = this.email,
       password = this.password,
       role = this.role,
   )
}

enum class Role {
    ADMIN,
    USER
}