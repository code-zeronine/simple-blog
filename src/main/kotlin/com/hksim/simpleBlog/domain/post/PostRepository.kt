package com.hksim.simpleBlog.domain.post

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>, CustomPostRepository

interface CustomPostRepository {
    fun findPosts(pageable: Pageable): Page<Post?>
}

class CustomPostRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor
) : CustomPostRepository {
    override fun findPosts(pageable: Pageable): Page<Post?> {
        return jpqlExecutor.findPage(pageable) {
            select(entity(Post::class))
                .from(entity(Post::class))
                .orderBy(path(Post::id).desc())
        }
    }
}
