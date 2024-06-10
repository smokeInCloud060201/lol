package vn.com.lol.nautilus.modules.firstdb.token;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token, Long> {

    @Query("SELECT t FROM Token t WHERE t.accessToken = ?1")
    Optional<Token> findByToken(String token);
}
