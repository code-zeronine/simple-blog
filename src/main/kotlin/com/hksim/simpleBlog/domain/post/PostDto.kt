package com.hksim.simpleBlog.domain.post

import com.hksim.simpleBlog.domain.member.Member
import com.hksim.simpleBlog.domain.member.MemberRes

data class PostReq(
    val title: String,
    val content: String,
    val memberId: Long
)

fun PostReq.toEntity() = Post(title, content, Member.createFakeMember(memberId))

data class PostRes(
    val id: Long,
    val title: String,
    val content: String,
    val member: MemberRes
)