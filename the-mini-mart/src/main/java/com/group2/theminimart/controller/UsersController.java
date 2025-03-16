package com.group2.theminimart.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.entity.Users;
import com.group2.theminimart.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.createUsers(user);
    }

    @GetMapping
    public ArrayList<Users> getUsers() {
        return (ArrayList<Users>) usersService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {
        return usersService.getUsers(id);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersService.updateUsers(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUsers(id);
    }
}
