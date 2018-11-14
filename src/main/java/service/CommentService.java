package service;

import model.Comment;

public interface CommentService {
    void addComment(Comment comment);
    void removeComment(Comment comment);
    void editComment(Comment comment);
}
