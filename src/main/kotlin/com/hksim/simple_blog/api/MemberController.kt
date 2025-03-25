package com.hksim.simple_blog.api

import com.hksim.simple_blog.domain.member.Member
import com.hksim.simple_blog.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/members")
    fun findAll(): MutableList<Member> = memberService.findAll()
}