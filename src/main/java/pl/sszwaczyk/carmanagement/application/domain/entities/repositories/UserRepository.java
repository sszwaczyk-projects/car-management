package pl.sszwaczyk.carmanagement.application.domain.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.sszwaczyk.carmanagement.application.domain.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
}
