package vn.com.lol.nautilus.modules.firstdb.token;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.common.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token, Long> {

    @Query("SELECT t FROM Token t " +
            " WHERE (t.accessToken = ?1 OR t.refreshToken = ?1) " +
            " AND (t.refreshToken = ?2 OR t.accessToken = ?2) " +
            " AND t.clientId = ?3")
    Optional<Token> findByToken(String token, String refreshToken, String clientId);

    @Query("SELECT t FROM Token t " +
            " WHERE t.accessToken = ?1 OR t.refreshToken = ?1")
    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t " +
            " WHERE t.tokenId = ?1")
    Optional<Token> findByTokenId(String id);
}
