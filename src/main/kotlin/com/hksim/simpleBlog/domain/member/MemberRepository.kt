package com.hksim.simpleBlog.domain.member

import com.hksim.simpleBlog.config.jdsl.CustomLimitJpql
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, CustomMemberRepository

interface CustomMemberRepository {
    fun findMembers(pageable: Pageable): Page<Member?>
    fun findByFilter(filter: String, count: Int): List<Member>
}

class CustomMemberRepositoryImpl(
    private val jdslJpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomMemberRepository {

    override fun findMembers(pageable: Pageable): Page<Member?> {
        val query = jpql {
            select(
                entity(Member::class)
            ).from(
                entity(Member::class)
            ).orderBy(
                path(Member::id).asc()
            )
        }
        return jdslJpqlExecutor.findPage(pageable) { query }
    }

    override fun findByFilter(filter: String, count: Int): List<Member> {
        return jdslJpqlExecutor.findAll(CustomLimitJpql) {
            select(entity(Member::class))
                .from(entity(Member::class))
                .where(
                    path(Member::email).like("${filter}%"),
                )
                .orderBy(path(Member::updateAt).desc())
                .limit(count)
        }.filterNotNull()
    }
}
