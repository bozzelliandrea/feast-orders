package business.service;

import arch.service.BaseCRUDService;
import atomic.entity.Category;
import atomic.entity.PrinterCfg;
import atomic.enums.CategoryProcessingZone;
import atomic.repository.CategoryRepository;
import business.converter.CategoryConverter;
import business.dto.CategoryDTO;
import business.dto.PrinterCfgDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService extends BaseCRUDService<Category, Long> {

    private final CategoryConverter converter;
    private final PrinterCfgService printerCfgService;

    public CategoryService(CategoryRepository repository,
                           CategoryConverter converter,
                           PrinterCfgService printerCfgService) {
        super(repository);
        this.converter = converter;
        this.printerCfgService = printerCfgService;
    }

    public List<CategoryDTO> getAll() {
        return super.findAll().stream().map(converter::convertEntity).collect(Collectors.toList());
    }

    public CategoryDTO get(Long id) {
        return converter.convertEntity(super.read(id));
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
        return converter.convertEntity(savedEntity);
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
        return converter.convertEntity(updatedCategory);
    }

    public CategoryDTO remove(Long id) {
        super.delete(id);
        return new CategoryDTO();
    }

}
