package com.hksim.simpleBlog.config

import com.hksim.simpleBlog.domain.member.*
import com.hksim.simpleBlog.domain.post.Post
import com.hksim.simpleBlog.domain.post.PostRepository
import com.hksim.simpleBlog.domain.post.PostReq
import com.hksim.simpleBlog.domain.post.toEntity
import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.serpro69.kfaker.faker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

private val log = KotlinLogging.logger {}

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository,
) {

    val faker = faker { }

    @EventListener(ApplicationReadyEvent::class)
    private fun init() {
//        val members = generateMembers(100)
//        memberRepository.saveAll(members)
//        val posts = generatePosts(100)
//        postRepository.saveAll(posts)
    }

    private fun generateMembers(cnt: Int): MutableList<Member> {
        val members = mutableListOf<Member>()

        (1..cnt).map {
            val member = generateMember()
            log.info { "insert $member" }
            members.add(member)
        }

        return members
    }

    private fun generatePosts(cnt: Int): MutableList<Post> {
        val posts = mutableListOf<Post>()

        (1..cnt).map {
            val post = generatePosts()
            log.info { "insert $post" }
            posts.add(post)
        }

        return posts
    }

    private fun generateMember() = MemberReq(
        faker.internet.safeEmail(),
        "1234",
        Role.USER
    ).toEntity()

    private fun generatePosts(): Post = PostReq(
        faker.theExpanse.ships(),
        faker.quote.matz(),
        1L
    ).toEntity()
}
