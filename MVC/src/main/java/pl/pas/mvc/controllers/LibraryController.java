package pl.pas.mvc.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pas.dto.create.UserCreateDTO;
import pl.pas.mvc.services.implementations.BookMvcService;
import pl.pas.mvc.services.implementations.UserMvcService;
import pl.pas.rest.model.Book;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class LibraryController {

    private final BookMvcService bookMvcService;
    private final UserMvcService userMvcService;

    @GetMapping
    public String getMainPage() {
        return "main";
    }

    @GetMapping("/books")
    public String getBooksPage(Model model) {
        List<Book> books = bookMvcService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("userCreateDTO",UserCreateDTO.builder().build());
        return "register";
    }

    @PostMapping("/submit")
    public String registerUser(@Valid @ModelAttribute UserCreateDTO userCreateDTO, RedirectAttributes redirectAttributes) {

        String result = userMvcService.registerUser(userCreateDTO);

        if (!result.equals("success")) {
            redirectAttributes.addFlashAttribute("error", result);
            // todo zrobic aby nie czysciło formularza po powtórzeniu emaila?
            // redirectAttributes.addFlashAttribute("userCreateDTO", userCreateDTO); nie dziala :(
        }
        return "redirect:/register";
    }

}
