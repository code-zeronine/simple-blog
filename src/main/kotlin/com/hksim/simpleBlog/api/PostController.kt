package com.hksim.simpleBlog.api

import com.hksim.simpleBlog.domain.post.PostReq
import com.hksim.simpleBlog.service.PostService
import com.hksim.simpleBlog.util.value.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/posts")
    fun findPosts(@PageableDefault(size = 10) pageable: Pageable): CmResDto<*> =
        CmResDto(HttpStatus.OK, "find All Posts", postService.findPosts(pageable))

    @GetMapping("/post/{id}")
    fun findMemberById(@PathVariable id: Long): CmResDto<*> =
        CmResDto(HttpStatus.OK, "find member by id", postService.findPostById(id))

    @DeleteMapping("/post/{id}")
    fun deleteMemberById(@PathVariable id: Long): CmResDto<*> =
        CmResDto(HttpStatus.OK, "delete member by id", postService.deletePostById(id))

    @PostMapping("/post")
    fun save(@RequestBody dto: PostReq): CmResDto<*> =
        CmResDto(HttpStatus.OK, "register member", postService.savePost(dto))
}