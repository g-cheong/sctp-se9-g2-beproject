package com.group2.theminimart.users;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UsersServiceImpl implements UsersService{
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users createUsers(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public ArrayList<Users> getUsers() {
        return (ArrayList<Users>) usersRepository.findAll();
    }

    @Override
    public Users getUsers(Long id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public Users updateUsers(Long id, Users users) {
        Users updatedUsers = usersRepository.findById(id).get();
        updatedUsers.setPassword(users.getPassword());
        return usersRepository.save(updatedUsers);
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }
}
