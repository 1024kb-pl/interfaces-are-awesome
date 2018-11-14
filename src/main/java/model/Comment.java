package model;

import java.util.Objects;

public class Comment {
    private Long id;
    private String owner;
    private String text;

    public Comment(Long id, String owner, String text) {
        this.id = id;
        this.owner = owner;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(owner, comment.owner) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, text);
    }

    @Override
    public String toString() {
        return id + "|" + owner + "|" + text;
    }
}
