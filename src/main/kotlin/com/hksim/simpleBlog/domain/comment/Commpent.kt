package com.hksim.simpleBlog.domain.comment

import com.hksim.simpleBlog.domain.AuditingEntity
import com.hksim.simpleBlog.domain.post.Post
import jakarta.persistence.*

@Entity
@Table(name = "Comment")
class Commpent(
    content: String,
    post: Post,
) : AuditingEntity() {
    @Column(name = "content", nullable = false)
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post::class)
    var post: Post = post
        protected set
}