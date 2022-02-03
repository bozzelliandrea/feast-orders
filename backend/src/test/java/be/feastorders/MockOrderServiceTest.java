package be.feastorders;

import be.feastorders.order.entity.Order;
import be.feastorders.order.repository.OrderRepository;
import be.feastorders.order.service.OrderService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static be.feastorders.MockDataUtils.generateOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class MockOrderServiceTest extends AbstractJpaDataProvider {

    private OrderService orderService;

    @BeforeAll
    public void setup() {
        orderService = new OrderService((OrderRepository) super.getRepository(Order.class));
        super.load(generateOrder());
        super.load(generateOrder());
    }

    @AfterAll
    public void clean() {
        super.clean();
    }

    @Test
    public void test() {
        Assertions.assertEquals(orderService.findAll().size(), 2L);
    }
}
