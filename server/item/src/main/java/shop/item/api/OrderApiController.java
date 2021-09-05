package shop.item.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shop.item.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
}
