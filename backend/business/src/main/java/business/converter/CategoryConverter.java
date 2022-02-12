package business.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.Category;
import atomic.enums.CategoryProcessingZone;
import business.dto.CategoryDTO;
import business.dto.PrinterCfgDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDTO> implements Converter {

    @Override
    public CategoryDTO convertEntity(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        convertEntity2DTO(entity, dto);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setColor(entity.getColor());
        dto.setProcessingZone(entity.getProcessingZone().name());

        if (entity.getPrinterCfgs() != null && !entity.getPrinterCfgs().isEmpty()) {
            dto.setPrinterCfgList(entity.getPrinterCfgs().stream()
                    .map(PrinterCfgDTO::new)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    @Override
    public Category convertDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setProcessingZone(CategoryProcessingZone.valueOf(categoryDTO.getProcessingZone()));
        category.setColor(categoryDTO.getColor());
        convertDTO2BaseEntity(categoryDTO, category);
        return category;
    }
}
