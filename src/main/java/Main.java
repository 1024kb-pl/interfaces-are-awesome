import dao.CommentDao;
import dao.CommentDaoDatabase;
import dao.CommentDaoFile;
import dao.CommentDaoLocalStorage;
import model.Comment;
import service.CommentService;
import service.CommentServiceImpl;

public class Main {
    public static void main(String[] args) {
        CommentDao databaseDao = new CommentDaoDatabase();
        CommentDao fileDao = new CommentDaoFile();
        CommentDao localStorage = new CommentDaoLocalStorage();


        CommentService commentServiceUsingDatabase = new CommentServiceImpl(databaseDao);
        CommentService commentServiceUsingFile = new CommentServiceImpl(fileDao);
        CommentService commentServiceUsingLocalStorage = new CommentServiceImpl(localStorage);

        Comment comment = new Comment(1L, "Pablo", "Interfaces are awesome!!!");

        commentServiceUsingDatabase.addComment(comment);
        commentServiceUsingFile.addComment(comment);
        commentServiceUsingLocalStorage.addComment(comment);
    }
}
