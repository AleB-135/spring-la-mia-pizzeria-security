package org.lessons.java.spring.crud.pizzeria_crud.controller;

import org.lessons.java.spring.crud.pizzeria_crud.model.Offers;
import org.lessons.java.spring.crud.pizzeria_crud.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
    
    @Autowired
    private OffersRepository offerRepository;

    @PostMapping
    public String store(@Valid @ModelAttribute("offering") Offers offerForm, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("offering", offerForm);
            return "offerings/create";
        }

        //Se non ho errori creo il prestito
        offerRepository.save(offerForm);

        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        offerRepository.deleteById(id);
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("offering", offerRepository.findById(id).get());
        return "offerings/edit";
    }

    @PostMapping("/{id}")
    public String update (@PathVariable Integer id, @Valid @ModelAttribute("offering") Offers offeringForm, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("offering", offeringForm);
            return "offerings/edit";
        }

        offerRepository.save(offeringForm);

        return "redirect:/pizze";
    }

    
}