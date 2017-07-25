package com.cisco.cmad.blog.data;

import com.cisco.cmad.blog.api.Blog;

public interface BlogDAO {
    public Blog create(Blog blog);

    public Blog read(long id);

    public void update(Blog blog);

    public void delete(long id);

}
