package com.tracom.events.scheduler.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.user_id = ?1")
    User findByUser_name(String user_name);
}
