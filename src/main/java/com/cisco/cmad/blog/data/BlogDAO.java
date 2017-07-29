package com.cisco.cmad.blog.data;

import java.util.List;

import com.cisco.cmad.blog.api.Blog;

public interface BlogDAO {
    public Blog create(Blog blog);

    public Blog read(long id);

    public void update(Blog blog);

    public void delete(long id);
    
    public Blog readFullEntity(long id);
    
    public List<Blog> readByCategory(String category, int pageNum);

    public List<Blog> readAllBlogs(int page,int size);
    
    public List<Blog> readAllBlogsFullEntity(int page,int size);
    
    public List<Blog> readByUserId(String userId, int pageNum);

}
