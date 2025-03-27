package com.hksim.simpleBlog.service

import com.hksim.simpleBlog.domain.member.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional(readOnly = true)
    fun findMembers(pageable: Pageable): Page<MemberRes> = memberRepository.findMembers(pageable).map { it?.toDto() }

    @Transactional(readOnly = true)
    fun getByFilter(filter: String, count: Int): List<Member> = memberRepository.findByFilter(filter, count)

    @Transactional
    fun saveMember(dto: MemberReq): MemberRes = memberRepository.save(dto.toEntity()).toDto()

    @Transactional
    fun deleteMemberById(id: Long) = memberRepository.deleteById(id)

    @Transactional
    fun findMemberById(id: Long): MemberRes =
        memberRepository.findById(id).orElseThrow { throw RuntimeException("Member not found") }.toDto()
}