package pl.pas.mvc.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pas.mvc.forms.EndRemoveRentForm;
import pl.pas.mvc.forms.RentForm;
import pl.pas.mvc.forms.RentNowForm;
import pl.pas.mvc.services.implementations.RentMvcService;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
@RequestMapping("/rents")
public class RentMvcController {

    private final RentMvcService rentMvcService;

    @GetMapping("/")
    public String rents() {
        return "redirect:/rents/active";
    }

    @GetMapping("/active")
    public String getActiveRents(Model model) {
        model.addAttribute("active", rentMvcService.getAllActive());
        model.addAttribute("currentPath", "/rents/active");
        if (!model.containsAttribute("endRemoveRentForm")) {
            model.addAttribute("endRemoveRentForm", EndRemoveRentForm.builder().build());
        }
        return "rents/active";
    }

    @GetMapping("/archive")
    public String archiveRents(Model model) {
        model.addAttribute("archive", rentMvcService.getAllArchive());
        return "rents/archive";
    }

    @GetMapping("/future")
    public String futureRents(Model model) {
        model.addAttribute("future", rentMvcService.getAllFuture());
        model.addAttribute("currentPath", "/rents/future");
        if (!model.containsAttribute("endRemoveRentForm")) {
            model.addAttribute("endRemoveRentForm", EndRemoveRentForm.builder().build());
        }
        return "rents/future";
    }

    @PostMapping("/endRent")
    public String endRent(@ModelAttribute EndRemoveRentForm endRentForm, RedirectAttributes redirectAttributes) {
        System.out.println("RentId: "+endRentForm.rentId());
        String output = rentMvcService.endRent(endRentForm);
        if (!Objects.equals(output, "rent.successfully.ended")) {
            redirectAttributes.addFlashAttribute("endRemoveRentForm", endRentForm);
            redirectAttributes.addFlashAttribute("error", output);
        }
        else {
            redirectAttributes.addFlashAttribute("success", output);
        }

        return "redirect:/rents/active";
    }

    @PostMapping("/removeRent")
    public String removeRent(@ModelAttribute EndRemoveRentForm removeRentForm,
                             @RequestParam("originPath") String originPath, RedirectAttributes redirectAttributes) {
        System.out.println("RentId: "+removeRentForm.rentId());
        String output = rentMvcService.removeRent(removeRentForm);
        if (!Objects.equals(output, "rent.successfully.deleted")) {
            redirectAttributes.addFlashAttribute("endRemoveRentForm", removeRentForm);
            redirectAttributes.addFlashAttribute("error", output);
        }
        else {
            redirectAttributes.addFlashAttribute("success", output);
        }

        return "redirect:" + originPath;
    }

    @PostMapping("/rent")
    public String rentBook(@Valid @ModelAttribute RentForm rentForm, RedirectAttributes redirectAttributes) {
        System.out.println("BookId" + rentForm.bookId());
        System.out.println("Email" + rentForm.email());
        System.out.println("BeginTime" + rentForm.beginTime());
        System.out.println("EndTime" + rentForm.endTime());
        String output = rentMvcService.createRent(rentForm);
        if (!Objects.equals(output, "rent.successfully.created")) {
            redirectAttributes.addFlashAttribute("error", output);
            redirectAttributes.addFlashAttribute("rentForm", rentForm);
            redirectAttributes.addFlashAttribute("showRentModal", true);
        }
        else {
            redirectAttributes.addFlashAttribute("success", output);
        }
        return "redirect:/books";
    }

    @PostMapping("/rent/now")
    public String rentBookNow(@Valid @ModelAttribute RentNowForm rentNowForm, RedirectAttributes redirectAttributes) {
        System.out.println("BookId" + rentNowForm.bookId());
        System.out.println("Email" + rentNowForm.email());
        String output = rentMvcService.createRentNow(rentNowForm);
        if (!Objects.equals(output, "rent.successfully.created")) {
            redirectAttributes.addFlashAttribute("error", output);
            redirectAttributes.addFlashAttribute("rentNowForm", rentNowForm);
            redirectAttributes.addFlashAttribute("showRentNowModal", true);
        }
        else {
            redirectAttributes.addFlashAttribute("success", output);
        }
        return "redirect:/books";
    }
}
