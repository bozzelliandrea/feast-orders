package business.discount.service;

import arch.service.BaseCRUDService;
import atomic.entity.Discount;
import atomic.repository.DiscountRepository;
import business.discount.converter.DiscountConverter;
import business.discount.dto.DiscountDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService extends BaseCRUDService<Discount, Long> {

    private final DiscountConverter discountConverter;

    public DiscountService(DiscountRepository repository, DiscountConverter discountConverter) {
        super(repository);
        this.discountConverter = discountConverter;
    }

    public List<DiscountDTO> getAll() {
        return super.findAll().stream()
                .map(discountConverter::convertEntity)
                .collect(Collectors.toList());
    }

    public DiscountDTO get(Long id){
        Discount category = super.read(id);
        return discountConverter.convertEntity(category);
    }

    public DiscountDTO create(DiscountDTO request){
        Discount discount = discountConverter.convertDTO(request);
        Discount savedEntity = super.create(discount);
        return discountConverter.convertEntity(savedEntity);
    }

    public DiscountDTO update(DiscountDTO request){
        Discount discount = discountConverter.convertDTO(request);
        Discount savedEntity = super.update(discount);
        return discountConverter.convertEntity(savedEntity);
    }

    public DiscountDTO remove(Long id) {
        super.delete(id);
        return new DiscountDTO();
    }
}
