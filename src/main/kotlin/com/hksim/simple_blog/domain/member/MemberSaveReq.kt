package com.hksim.simple_blog.domain.member

/**
 * dto <-> entity 간의 맵핑할 때, 크게 스타일이 2개 있는 것 같음.
 *
 * 1. 각 dto, entity 에 책임 할당.
 * 2. entitymapper 라는 것을 만들어서 담당하도록 함.
 */
data class MemberSaveReq(
    val email: String,
    val password: String,
    val role: Role
)

fun MemberSaveReq.toEntity() = Member(email, password, role)
