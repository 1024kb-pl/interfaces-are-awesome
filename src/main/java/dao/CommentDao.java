package dao;

import model.Comment;

import java.util.Collection;
import java.util.Optional;

public interface CommentDao {
    Comment save(Comment comment);

    Collection<Comment> getAllComments();
    Optional<Comment> getCommentById(Long id);
    Collection<Comment> getCommentsByOwner(String owner);

    void deleteCommentById(Long id);
    void deleteCommentsByOwner(String owner);

    Comment update(Comment comment);
}
