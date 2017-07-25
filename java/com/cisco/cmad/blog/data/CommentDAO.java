package com.cisco.cmad.blog.data;

import com.cisco.cmad.blog.api.Comment;

public interface CommentDAO {
    public Comment create(long blogId, Comment comment);

    public Comment read(long commentId);

    public void update(Comment comment);

    public void delete(long id);

}
