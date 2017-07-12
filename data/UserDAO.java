package com.cisco.cmad.blog.data;

import com.cisco.cmad.blog.api.User;

public interface UserDAO {
    public void create(User user);

    public User read(String userId);

    public void update(User user);

    public void delete(String userId);
}
