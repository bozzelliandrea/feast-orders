package be.feastorders.business.site.service;

import be.feastorders.business.site.entity.Site;
import be.feastorders.business.site.repository.SiteRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class SiteService extends BaseCRUDService<Site, Long> {

    public SiteService(SiteRepository repository) {
        super(repository);
    }
}
