package be.feastorders.business.site.service;

import be.feastorders.business.site.entity.Site;
import be.feastorders.business.site.repository.SiteRepository;
import be.feastorders.business.core.service.BaseCRUDService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(4)
public class SiteService extends BaseCRUDService<Site, Long> {

    public SiteService(SiteRepository repository) {
        super(repository);
    }
}
