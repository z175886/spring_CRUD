package com.chuyang.app.rest.ApiControllers;

import com.chuyang.app.rest.Models.User;
import com.chuyang.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";
    }
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    @PostMapping (value = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "Saved...";
    }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updateUser = userRepo.findById(id).get();
        updateUser.setFirstname(user.getFirstname());
        updateUser.setLastname(user.getLastname());
        updateUser.setOccupation(user.getOccupation());
        updateUser.setAge(user.getAge());
        userRepo.save(updateUser);
        return "updating...";
    }
    @DeleteMapping(value="/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleting id: " + id;


    }
}
