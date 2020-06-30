package com.zhaiqianfeng.user;

public interface UserMapper {
    User getUserById(int id);
    int insertUser(User user);
}
