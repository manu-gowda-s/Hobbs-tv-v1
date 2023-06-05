package com.hobbs.tv.repo;

import com.hobbs.tv.entity.Country;
import com.hobbs.tv.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long>
{
    Optional<User> findByEmail(String email);

    //QUERY
    @Query("{country: ?0}")
    List<User> findByCountry(Country country);
}
