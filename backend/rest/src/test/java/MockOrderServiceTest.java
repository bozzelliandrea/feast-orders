import atomic.entity.Order;
import atomic.repository.OrderRepository;
import business.order.service.OrderService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FeastOrdersApplication.class)
public class MockOrderServiceTest extends AbstractJpaDataProvider {

    private OrderService orderService;

    @BeforeAll
    public void setup() {
        orderService = new OrderService((OrderRepository) super.getRepository(Order.class));
        super.load(MockDataUtils.generateOrder());
        super.load(MockDataUtils.generateOrder());
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
