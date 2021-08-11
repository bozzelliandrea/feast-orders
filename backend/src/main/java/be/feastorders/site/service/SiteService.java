package be.feastorders.site.service;

import be.feastorders.site.entity.Site;
import be.feastorders.site.repository.SiteRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(4)
public class SiteService extends BaseCRUDService<Site, Long> {

    public SiteService(SiteRepository repository) {
        super(repository);
    }
}
