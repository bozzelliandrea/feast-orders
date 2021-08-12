package be.feastorders.site.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.site.entity.Site;
import be.feastorders.site.repository.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteService extends BaseCRUDService<Site, Long> {

    public SiteService(SiteRepository repository) {
        super(repository);
    }
}
