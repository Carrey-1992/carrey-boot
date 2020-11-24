package com.example.carrey.transaction;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {

    void testSave(User user);

    List<User> testQueryUserList();
}
