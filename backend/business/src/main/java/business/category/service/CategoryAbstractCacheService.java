package business.category.service;

import arch.cache.AbstractCache;
import business.category.dto.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryAbstractCacheService extends AbstractCache<Long, CategoryDTO> {

    public static final String CACHE_NAME = "CategoryCache";

    private static final Logger _LOGGER = LoggerFactory.getLogger(CategoryAbstractCacheService.class);

    public CategoryAbstractCacheService() {
        super(CACHE_NAME);
    }

    public void loadCategories(List<CategoryDTO> categories) {
        _LOGGER.info("Load in cache all categories");
        for (CategoryDTO c : categories) {
            super.putCacheData(c.getId(), c, false);
        }
    }
}
