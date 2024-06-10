package vn.com.lol.yorick.user.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.repository.BaseRepository;
import vn.com.lol.yorick.user.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Query("SELECT u from User u WHERE UPPER(u.email) = UPPER(?1)")
    Optional<User> findByUserName(String userName);
}
