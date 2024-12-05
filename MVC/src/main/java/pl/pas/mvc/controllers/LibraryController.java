package pl.pas.mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LibraryController {

    @GetMapping
    public String getMainPage() {
        return "main";
    }

}
