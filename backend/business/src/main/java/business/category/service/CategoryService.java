package business.category.service;

import arch.cache.CacheElementNotFoundException;
import arch.service.BaseCRUDService;
import atomic.entity.Category;
import atomic.entity.PrinterCfg;
import atomic.enums.CategoryProcessingZone;
import atomic.repository.CategoryRepository;
import business.category.converter.CategoryConverter;
import business.category.dto.CategoryDTO;
import business.printer.dto.PrinterCfgDTO;
import business.printer.service.PrinterCfgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService extends BaseCRUDService<Category, Long> {

    private final CategoryConverter converter;
    private final PrinterCfgService printerCfgService;
    private final CategoryCacheService categoryCacheService;

    public CategoryService(CategoryRepository repository,
                           CategoryConverter converter,
                           PrinterCfgService printerCfgService,
                           CategoryCacheService categoryCacheService) {
        super(repository);
        this.converter = converter;
        this.printerCfgService = printerCfgService;
        this.categoryCacheService = categoryCacheService;
    }

    public List<CategoryDTO> getAll() {
        if (categoryCacheService.count() == 0) {
            List<CategoryDTO> categories = super.findAll().stream().map(converter::convertEntity).collect(Collectors.toList());
            categoryCacheService.loadCategories(categories);
            return categories;
        } else {
            return categoryCacheService.getAll();
        }
    }

    public CategoryDTO get(Long id) {
        CategoryDTO dto;

        try {
            dto = categoryCacheService.getCacheData(id);
        } catch (CacheElementNotFoundException e) {
            Category category = super.read(id);
            dto = converter.convertEntity(category);
            categoryCacheService.putCacheData(dto.getId(), dto);
        }
        return dto;
    }

    public CategoryDTO create(CategoryDTO dto) {
        Category category = converter.convertDTO(dto);

        if (dto.getPrinterCfgList() != null && !dto.getPrinterCfgList().isEmpty()) {
            List<PrinterCfg> printerCfgList = dto.getPrinterCfgList()
                    .stream()
                    .map(d -> printerCfgService.read(d.getId()))
                    .collect(Collectors.toList());
            category.getPrinterCfgs().addAll(printerCfgList);
        }

        Category savedEntity = super.create(category);
        CategoryDTO newDto = converter.convertEntity(savedEntity);
        categoryCacheService.putCacheData(newDto.getId(), newDto);
        return newDto;
    }

    public CategoryDTO update(CategoryDTO dto) {
        Category savedCategory = super.read(dto.getId());
        savedCategory.setName(dto.getName());
        savedCategory.setColor(dto.getColor());
        savedCategory.setProcessingZone(CategoryProcessingZone.valueOf(dto.getProcessingZone()));
        savedCategory.setDescription(dto.getDescription());

        List<PrinterCfg> printerCfgList = new ArrayList<>();
        if (dto.getPrinterCfgList() != null) {
            if (!dto.getPrinterCfgList().isEmpty()) {
                for (PrinterCfgDTO printerCfgDTO : dto.getPrinterCfgList()) {
                    Optional<PrinterCfg> printerCfgOptional = savedCategory.getPrinterCfgs()
                            .stream()
                            .filter(printerCfg -> printerCfg.getId().equals(printerCfgDTO.getId()))
                            .findFirst();

                    printerCfgList.add(printerCfgOptional.orElseGet(() -> printerCfgService.read(printerCfgDTO.getId())));
                }
            }
        }
        savedCategory.setPrinterCfgs(printerCfgList);

        Category updatedCategory = super.update(savedCategory);
        CategoryDTO newDto = converter.convertEntity(updatedCategory);
        categoryCacheService.putCacheData(newDto.getId(), newDto);
        return newDto;
    }

    public CategoryDTO remove(Long id) {
        categoryCacheService.removeCacheData(id);
        super.delete(id);
        return new CategoryDTO();
    }

}
