package com.hksim.simpleBlog.domain.post

import com.hksim.simpleBlog.domain.member.Member
import com.hksim.simpleBlog.domain.member.MemberRes
import jakarta.validation.constraints.NotNull

data class PostReq(
    @field:NotNull(message = "title must not be null")
    val title: String?,
    val content: String?,
    @field:NotNull(message = "memberId must not be null")
    val memberId: Long?
)

fun PostReq.toEntity(): Post = Post(title ?: "", content ?: "", Member.createFakeMember(memberId!!))

data class PostRes(
    val id: Long,
    val title: String,
    val content: String,
    val member: MemberRes
)