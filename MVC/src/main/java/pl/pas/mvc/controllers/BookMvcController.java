package pl.pas.mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pas.mvc.forms.RentForm;
import pl.pas.mvc.forms.RentNowForm;
import pl.pas.mvc.services.implementations.BookMvcService;
import pl.pas.rest.model.Book;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookMvcController {

    private final BookMvcService bookMvcService;

    @GetMapping()
    public String getBooksPage(Model model) {
        List<Book> books = bookMvcService.findAll();
        model.addAttribute("books", books);
        if (!model.containsAttribute("rentForm")) {
            model.addAttribute("rentForm", RentForm.builder().build());
        }
        if (!model.containsAttribute("rentNowForm")) {
            model.addAttribute("rentNowForm", RentNowForm.builder().build());
        }
        return "books";
    }

    @GetMapping("search")
    @ResponseBody
    public List<Book> getBooksByTitle(@RequestParam("title") String title, Model model) {
        return bookMvcService.findAllByTitle(title);
    }
}
