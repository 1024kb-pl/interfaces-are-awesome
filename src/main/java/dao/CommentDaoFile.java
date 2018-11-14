package dao;

import model.Comment;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CommentDaoFile implements CommentDao {
    private final static String FILE_NAME = "comments.txt";

    public CommentDaoFile() {
        File file = new File(FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(FILE_NAME);
        pw.close();
    }

    private void save(Collection<Comment> comments) {
        try {
            clearFile();
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(FILE_NAME, true));
            for(Comment comment : comments) {
                printWriter.write(comment.toString() + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Comment save(Comment comment) {
        List<Comment> commentList = new LinkedList<>(getAllComments());
        commentList.add(comment);
        save(commentList);
        return comment;
    }

    @Override
    public Collection<Comment> getAllComments() {
        List<Comment> commentList = new ArrayList<>();

        try {
            BufferedReader  bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String readLine = bufferedReader.readLine();

            while(readLine != null) {
                String [] values = readLine.split("|");
                Comment comment = new Comment(Long.parseLong(values[0]), values[1], values[2]);
                commentList.add(comment);

                readLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return commentList;
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return getAllComments().stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Comment> getCommentsByOwner(String owner) {
        return getAllComments().stream()
                .filter(comment -> comment.getOwner().equals(owner))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Long id) {
        List<Comment> commentList = new LinkedList<>(getAllComments());
        commentList.removeIf(comment -> comment.getId().equals(id));
        save(commentList);
    }

    @Override
    public void deleteCommentsByOwner(String owner) {
        List<Comment> commentList = new LinkedList<>(getAllComments());
        commentList.removeIf(comment -> comment.getOwner().equals(owner));
        save(commentList);
    }

    @Override
    public Comment update(Comment comment) {
        deleteCommentById(comment.getId());
        List<Comment> commentList = new LinkedList<>(getAllComments());
        commentList.add(comment);
        save(commentList);
        return comment;
    }
}
