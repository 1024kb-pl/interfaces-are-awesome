package dao;

import model.Comment;

import java.util.*;
import java.util.stream.Collectors;


public class CommentDaoLocalStorage implements CommentDao {
    private List<Comment> comments = new LinkedList<>();

    @Override
    public Comment save(Comment comment) {
        comments.add(comment);
        return comment;
    }

    @Override
    public Collection<Comment> getAllComments() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Comment> getCommentsByOwner(String owner) {
        return comments.stream()
                .filter(comment -> comment.getOwner().equals(owner))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Long id) {
        comments.removeIf(comment -> comment.getId().equals(id));
    }

    @Override
    public void deleteCommentsByOwner(String owner) {
        comments.removeIf(comment -> comment.getOwner().equals(owner));

    }

    @Override
    public Comment update(Comment comment) {
        deleteCommentById(comment.getId());
        save(comment);
        return comment;
    }
}
