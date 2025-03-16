package com.group2.theminimart.service;

import java.util.ArrayList;

import com.group2.theminimart.entity.Users;

public interface UsersService {
    public Users createUsers(Users Users);

    public ArrayList<Users> getUsers();

    public Users getUsers(Long id);

    public Users updateUsers(Long id, Users Users);

    public void deleteUsers(Long id);
}
