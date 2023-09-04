package com.example.CRUD.Repository;

import com.example.CRUD.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {

}
