package com.hksim.simple_blog.config

import com.hksim.simple_blog.domain.member.Member
import com.hksim.simple_blog.domain.member.MemberRepository
import com.hksim.simple_blog.domain.member.Role
import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.serpro69.kfaker.faker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

private val log = KotlinLogging.logger {}

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
) {

    val faker = faker { }

    @EventListener(ApplicationReadyEvent::class)
    private fun init() {

        val member = Member(
            email = faker.internet.safeEmail(),
            password = "1234",
            role = Role.USER,
        )

        log.info { "insert $member" }

        memberRepository.save(member)
    }
}
