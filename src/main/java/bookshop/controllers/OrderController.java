package bookshop.controllers;

import bookshop.entities.*;
import bookshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private BookOrderService bookOrderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "orders")
    String GetOrderDetails(Model model) {
        User user = userService.getCurrentUser();
        Long userId = user.getUser_id();

        List<Order> orders = orderService.getAllByUserId(userId);
        model.addAttribute("orderList", orders);

        return "orderList";
    }

    @RequestMapping(value = "orders/{id}")
    String getBookList(Model model, @PathVariable("id") Long orderId) {
        List<Long> bookIdsList = bookOrderService.getBookIds(orderId);

        List<Book> bookList = bookIdsList.stream()
                .map(bookService::getById)
                .collect(Collectors.toList());

        model.addAttribute("bookList", bookList);

        double sum = bookList
                .stream()
                .map(Book::getPrice)
                .mapToDouble(price -> price)
                .sum();

        model.addAttribute("sum", sum);
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping(value = "orderCart")
    String order(Model model) {
        Order order = new Order();
        Long userId = userService.getCurrentUser().getUser_id();
        order.setUser_id(userId);
        order.setPayed(false);

        List<Book> books = cartService.getBooks(userId);

        double sum = books
                .stream()
                .map(Book::getPrice)
                .mapToDouble(price -> price)
                .sum();


        order.setSum(sum);
        order.setStatus("processing");
        orderService.save(order);

        Long id = orderService.findTopByOrderByIdDesc();

        for (Book b :
                books) {
            BookToOrder bo = new BookToOrder();
            bo.setOrder_id(id);
            bo.setBook_id(b.getBook_id());
            bookOrderService.save(bo);
        }

        cartService.deleteAllByUserId(userId);

        model.addAttribute("sum", sum);
        return "redirect:/orders/" + id;
    }

    @RequestMapping(value = {"/toBooks", "/toBookList", "/back"})
    String toBooks() {
        return "redirect:/";
    }

    @RequestMapping(value = "/pay/{id}")
    String pay(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        order.setStatus("payed");
        orderService.update(id);
        return "payed";
    }
}



