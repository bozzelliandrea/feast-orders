package be.feastorders.business.user.service;

import be.feastorders.business.user.entity.User;
import be.feastorders.business.user.repository.UserRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseCRUDService<User, Long> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
