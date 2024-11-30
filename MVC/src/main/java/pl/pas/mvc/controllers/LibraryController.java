package pl.pas.mvc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pas.mvc.services.implementations.BookClient;
import pl.pas.rest.model.Book;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class LibraryController {

    private final BookClient bookClient;

    @GetMapping
    public String getMainPage() {
        return "main";
    }

    @RequestMapping("/books")
    public String getBooksPage(Model model) {
        List<Book> books = bookClient.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }

}
