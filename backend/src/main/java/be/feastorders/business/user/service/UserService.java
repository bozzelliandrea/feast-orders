package be.feastorders.business.user.service;

import be.feastorders.business.core.service.BaseCRUDService;
import be.feastorders.business.user.entity.User;
import be.feastorders.business.user.repository.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(5)
public class UserService extends BaseCRUDService<User, Long> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
