package com.tracom.events.scheduler.User;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository uRepo;
    //CREATE
    public void saveUser(User user){
        uRepo.save(user);
    }

    //RETRIEVE
    public List<User> listUsers(){
        return uRepo.findAll();
    }
    //DELETE
    public void deleteUser(long user_id){
        uRepo.deleteById(user_id);
    }

    //UPDATE
    public User updateUser(long user_id){
       return uRepo.findById(user_id).get();
    }


}
