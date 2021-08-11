package be.feastorders.user.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.user.entity.User;
import be.feastorders.user.repository.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(5)
public class UserService extends BaseCRUDService<User, Long> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
