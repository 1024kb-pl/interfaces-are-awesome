package service;

import dao.CommentDao;
import model.Comment;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    @Override
    public void addComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        commentDao.deleteCommentById(comment.getId());
    }

    @Override
    public void editComment(Comment comment) {
        commentDao.update(comment);
    }
}
