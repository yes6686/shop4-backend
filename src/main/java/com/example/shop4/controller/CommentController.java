package com.example.shop4.controller;

import com.example.shop4.dto.CommentDto;
import com.example.shop4.service.CommentPermissionService;
import com.example.shop4.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentPermissionService commentPermissionService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        CommentDto savedComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("goods/{id}")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable("id") Long goodsId){
        List<CommentDto> comments = commentService.getAllComments(goodsId);
        return ResponseEntity.ok(comments);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id,
                                                    @RequestBody CommentDto updatedComment){
        commentService.updateComment(id,updatedComment);
        System.out.println(id+" , "+updatedComment.getComment());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully.....!!!! 무야호~");
    }

    @PostMapping("/like/{commentId}/{memberId}")
    public ResponseEntity<Integer> likeOrUnlikeComment(
            @PathVariable Long commentId,
            @PathVariable Long memberId
    ){
        System.out.println("commentId = " + commentId);
        System.out.println("memberId = " + memberId);
        Integer isLike = commentService.likeOrUnlikeComment(commentId, memberId);

        return new ResponseEntity<>(isLike, HttpStatus.CREATED);
    }

    @GetMapping("/like/{commentId}/{memberId}")
    public ResponseEntity<Integer> getLike(
            @PathVariable Long commentId,
            @PathVariable Long memberId
    ){
        Integer isLike = commentService.getLike(commentId, memberId);
        return new ResponseEntity<>(isLike, HttpStatus.CREATED);
    }

    // 댓글 작성 권한을 확인하는 API
    @GetMapping("/can-comment/{memberId}/{goodsId}")
    public ResponseEntity<Boolean> canComment(@PathVariable Long memberId,
                                              @PathVariable Long goodsId){
        boolean canComment = commentPermissionService.canComment(memberId, goodsId);

        return ResponseEntity.ok(canComment);
    }

}
