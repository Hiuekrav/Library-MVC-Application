package pl.pas.mvc.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pas.dto.create.UserCreateDTO;
import pl.pas.mvc.services.implementations.UserMvcService;

@RequiredArgsConstructor
@Controller
public class UserMcvController {

    private final UserMvcService userMvcService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        if (!model.containsAttribute("userCreateDTO")) {
            model.addAttribute("userCreateDTO", UserCreateDTO.builder().build());
        }
        return "register";
    }

    @PostMapping("/submit")
    public String registerUser(@Valid @ModelAttribute UserCreateDTO userCreateDTO, RedirectAttributes redirectAttributes) {

        String result = userMvcService.registerUser(userCreateDTO);

        if (!result.equals("registered.successfully")) {
            redirectAttributes.addFlashAttribute("error", result);
            redirectAttributes.addFlashAttribute("userCreateDTO", userCreateDTO);
        }
        else {
            redirectAttributes.addFlashAttribute("success", result);
        }
        return "redirect:/register";
    }
}
