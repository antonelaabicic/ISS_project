package hr.algebra.keywordsuggestionsapi.controller.rest;

import hr.algebra.keywordsuggestionsapi.dto.AuthRequestDTO;
import hr.algebra.keywordsuggestionsapi.dto.JwtResponseDTO;
import hr.algebra.keywordsuggestionsapi.dto.RefreshTokenRequestDTO;
import hr.algebra.keywordsuggestionsapi.model.RefreshToken;
import hr.algebra.keywordsuggestionsapi.service.JwtService;
import hr.algebra.keywordsuggestionsapi.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getEmail());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequest.getEmail()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid login credentials.");
        }
    }

    @PostMapping("/refresh")
    public JwtResponseDTO refresh(@RequestBody RefreshTokenRequestDTO request) {
        return refreshTokenService.findByToken(request.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String newAccessToken = jwtService.generateToken(user.getEmail());
                    return JwtResponseDTO.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(request.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token not found in database."));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);
            refreshTokenService.deleteByUsername(email);
        } else {
            throw new RuntimeException("Missing or malformed Authorization header");
        }
    }
}
