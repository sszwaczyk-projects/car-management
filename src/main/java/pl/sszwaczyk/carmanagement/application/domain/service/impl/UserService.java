package pl.sszwaczyk.carmanagement.application.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sszwaczyk.carmanagement.application.domain.entities.User;
import pl.sszwaczyk.carmanagement.application.domain.entities.dto.UserDTO;
import pl.sszwaczyk.carmanagement.application.domain.entities.repositories.UserRepository;
import pl.sszwaczyk.carmanagement.application.domain.exception.EmailExistsException;
import pl.sszwaczyk.carmanagement.application.domain.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
