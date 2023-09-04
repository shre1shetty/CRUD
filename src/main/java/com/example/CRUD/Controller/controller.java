package com.example.CRUD.Controller;

import com.example.CRUD.Entity.Users;
import com.example.CRUD.Exception.UserNotFoundException;
import com.example.CRUD.Repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class controller {
    @Autowired
    UserRepo repo;

//    @Autowired
//    CsrfTokenRepository csrfTokenRepository;
//
//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        System.out.println(csrfTokenRepository.loadToken(request));
//        return csrfTokenRepository.loadToken(request);
//    }
    @PostMapping("/add")
    Users add(@RequestBody Users user){
        if (user.getName() == null || user.getName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException();
        }
        return repo.save(user);
    }
    @GetMapping("/AllUsers")
    List<Users> getAll(){
        return repo.findAll();
    }

    @GetMapping("/add/{id}")
    Users findbyid(@PathVariable Long id){

        return repo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/add/{id}")
    Users updatebyid(@RequestBody Users newUser, @PathVariable Long id){
        return repo.findById(id).map(
                 users->{
                     users.setName(newUser.getName());
                     users.setEmail(newUser.getEmail());
                     return repo.save(users);
                 }
                )
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/add/{id}")
    String deleteByid(@PathVariable Long id){
        if(!repo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        repo.deleteById(id);
        return "Deleted User of id: "+id;
    }

}
