package service;

import dao.CommentDao;
import model.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCommentServiceImpl {
    CommentDao commentDao;
    CommentService commentService;

    @Before
    public void setup() {
        commentDao = Mockito.mock(CommentDao.class);
        commentService = new CommentServiceImpl(commentDao);
    }

    @Test
    public void testAddComment() {
        //given
        Comment comment = new Comment(1l, "Pablo", "Default");

        //when
        commentService.addComment(comment);

        //expected
        Mockito.verify(commentDao, Mockito.times(1)).save(comment);
    }
}
