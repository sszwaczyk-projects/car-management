package pl.sszwaczyk.carmanagement.application.domain.service;

import pl.sszwaczyk.carmanagement.application.domain.entities.User;
import pl.sszwaczyk.carmanagement.application.domain.entities.dto.UserDTO;
import pl.sszwaczyk.carmanagement.application.domain.exception.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;
}
