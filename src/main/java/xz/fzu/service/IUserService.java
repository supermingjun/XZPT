package xz.fzu.service;

import xz.fzu.model.User;


public interface IUserService {
    void register(User user);

    User selectByEmail(String email);

    User selectByUserId(String email);

    void vertifyUser(User user);
}
