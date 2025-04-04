package com.hksim.simpleBlog.domain.post

import com.hksim.simpleBlog.domain.AuditingEntity
import com.hksim.simpleBlog.domain.member.Member
import com.hksim.simpleBlog.domain.member.toDto
import jakarta.persistence.*

@Entity
@Table(name = "Post")
class Post(
    title: String,
    content: String,
    member: Member
) : AuditingEntity() {

    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", nullable = true, length = 1_000)
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        protected set

    override fun toString(): String = "Post(id='$id', title='$title', content='$content', member=$member)"

}

fun Post.toDto(): PostRes = PostRes(id!!, title, content, member.toDto())