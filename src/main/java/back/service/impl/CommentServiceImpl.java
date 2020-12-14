package back.service.impl;

import back.dto.CommentDto;
import back.entity.Comment;
import back.repository.CommentRepository;
import back.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getLastComments() {
        return commentRepository.findAll();
    }
}
