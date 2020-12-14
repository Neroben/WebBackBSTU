package back.service;

import back.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getLastComments();

}
