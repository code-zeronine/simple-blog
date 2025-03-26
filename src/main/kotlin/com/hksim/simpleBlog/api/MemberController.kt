package com.hksim.simpleBlog.api

import com.hksim.simpleBlog.domain.member.MemberReq
import com.hksim.simpleBlog.domain.member.toDto
import com.hksim.simpleBlog.service.MemberService
import com.hksim.simpleBlog.util.value.CmResDto
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/members")
    fun findMembers(@PageableDefault(size = 10) pageable: Pageable): CmResDto<*> =
        CmResDto(HttpStatus.OK, "find All Members", memberService.findMembers(pageable))

    @GetMapping("/members/filter")
    fun filterMembers(filter: String, count: Int): CmResDto<*> =
        CmResDto(HttpStatus.OK, "find filtered Members", memberService.getByFilter(filter, count).map { it.toDto() })

    @GetMapping("/member/{id}")
    fun findMemberById(@PathVariable id: Long): CmResDto<*> =
        CmResDto(HttpStatus.OK, "find member by id", memberService.findMemberById(id))

    @DeleteMapping("/member/{id}")
    fun deleteMemberById(@PathVariable id: Long): CmResDto<*> =
        CmResDto(HttpStatus.OK, "delete member by id", memberService.deleteMemberById(id))

    @PostMapping("/member")
    fun save(@Valid @RequestBody dto: MemberReq): CmResDto<*> =
        CmResDto(HttpStatus.OK, "register member", memberService.saveMember(dto))
}

