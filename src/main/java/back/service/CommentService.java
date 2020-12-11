package back.service;

import back.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getLastComments();

}
