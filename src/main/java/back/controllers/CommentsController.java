package back.controllers;

import back.dto.CommentDto;
import back.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @GetMapping("/last")
    public ResponseEntity<List<CommentDto>> getLastComments(){
        return ResponseEntity.ok(commentService.getLastComments());
    }


}
