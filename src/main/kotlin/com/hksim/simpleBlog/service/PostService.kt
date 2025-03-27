package com.hksim.simpleBlog.service

import com.hksim.simpleBlog.domain.post.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository
) {
    @Transactional(readOnly = true)
    fun findPosts(pageable: Pageable): Page<PostRes> = postRepository.findPosts(pageable).map { it?.toDto() }

    @Transactional
    fun savePost(dto: PostReq): PostRes = postRepository.save(dto.toEntity()).toDto()

    @Transactional
    fun deletePostById(id: Long) = postRepository.deleteById(id)

    @Transactional
    fun findPostById(id: Long): PostRes =
        postRepository.findById(id).orElseThrow { throw RuntimeException("Post not found") }.toDto()
}