package com.hobbs.tv.repo;

import com.hobbs.tv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByUserNameAndPassword(String userName, String password);
}
