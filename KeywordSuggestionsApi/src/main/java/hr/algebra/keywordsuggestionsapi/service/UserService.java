package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.UserDTO;
import hr.algebra.keywordsuggestionsapi.model.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    List<UserDTO> findAllUsers();
}