package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.model.RefreshToken;
import hr.algebra.keywordsuggestionsapi.model.User;
import hr.algebra.keywordsuggestionsapi.repository.RefreshTokenRepository;
import hr.algebra.keywordsuggestionsapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String email) {
        var user = userRepository.findByEmail(email);

        refreshTokenRepository.findByUser(user).ifPresent(existingToken -> {
            refreshTokenRepository.delete(existingToken);
            refreshTokenRepository.flush();
        });

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public void deleteRefreshToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        if(refreshToken.isPresent()){
            refreshTokenRepository.delete(refreshToken.get());
        } else {
            throw new RuntimeException("Refresh Token is not in DB..!!");
        }
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    public void deleteByUsername(String email) {
        User user = userRepository.findByEmail(email);

        refreshTokenRepository.findByUser(user).ifPresent(refreshToken -> {
            refreshTokenRepository.delete(refreshToken);
            refreshTokenRepository.flush();
        });
    }

}