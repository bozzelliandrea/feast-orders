package business.category.service;

import arch.cache.AbstractCache;
import arch.cache.Cache;
import arch.cache.CachePolicy;
import arch.cache.RechargeableCache;
import atomic.repository.CategoryRepository;
import business.category.converter.CategoryConverter;
import business.category.dto.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static business.category.service.CategoryCacheService.CACHE_NAME;

@Cache(name = CACHE_NAME, policy = CachePolicy.REFRESH)
public class CategoryCacheService extends AbstractCache<Long, CategoryDTO> implements RechargeableCache {

    public static final String CACHE_NAME = "CategoryCache";

    private static final Logger _LOGGER = LoggerFactory.getLogger(CategoryCacheService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryConverter converter;

    public CategoryCacheService(CategoryRepository categoryRepository,
                                CategoryConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public void loadCategories(List<CategoryDTO> categories) {
        _LOGGER.info("Load in cache all categories");
        for (CategoryDTO c : categories) {
            super.putCacheData(c.getId(), c, false);
        }
    }

    @Override
    public void reload() {
        List<CategoryDTO> categories = categoryRepository.findAll()
                .stream()
                .map(converter::convertEntity)
                .collect(Collectors.toList());
        loadCategories(categories);
    }
}
