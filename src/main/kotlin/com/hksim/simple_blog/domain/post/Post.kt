package com.hksim.simple_blog.domain.post

import com.hksim.simple_blog.domain.AuditingEntity
import com.hksim.simple_blog.domain.member.Member
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

    @Column(name = "content", nullable = true)
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
    protected set
}