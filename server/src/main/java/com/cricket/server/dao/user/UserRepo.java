package com.cricket.server.dao.user;

import com.cricket.server.model.user.User;
import com.cricket.server.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    @Query("select (count(u) > 0) from User u where u.username = :username and u.userRole = :userRole")
    boolean checkExistingUserWithRole(@Param("username") String username, @Param("userRole") UserRole userRole);

}
