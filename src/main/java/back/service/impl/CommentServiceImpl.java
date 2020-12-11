package back.service.impl;

import back.dto.CommentDto;
import back.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public List<CommentDto> getLastComments() {
        return null;
    }
}
