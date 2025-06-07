package hr.algebra.keywordsuggestionsapi.service;

import hr.algebra.keywordsuggestionsapi.dto.UserDTO;
import hr.algebra.keywordsuggestionsapi.model.User;
import hr.algebra.keywordsuggestionsapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
