package org.lessons.java.spring.crud.pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring.crud.pizzeria_crud.model.Offers;
import org.lessons.java.spring.crud.pizzeria_crud.model.Pizza;
import org.lessons.java.spring.crud.pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring.crud.pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

    @Autowired // Forza la dipendency injection (Crea una classe temporanea che implementa
               // l'interfaccia e la inserisca in pizzaRepository)
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Authentication authentication, Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        // Mi aspetto di ricevere un parametro chiamato "keyword". Ciò che è salvato
        // dentro la chiave keyword dev'essere salvato d'ora in poi con la stringa
        // "keyword".
        List<Pizza> Pizza;
        if (keyword != null && !keyword.isEmpty()) {
            Pizza = pizzaRepository.findByNameContainingIgnoreCase(keyword);
            // Se la keyword non è vuota e sto cercando qualcosa nello specifico, torna in
            // repository e chiama il metodo sopracitato
            // "(findByNameContainingIgnoreCase(keyword))" riportando solo i nomi delle
            // pizze che abbiano al loro interno anche solo una parte del nome che
            // possiedono.
        } else {
            Pizza = pizzaRepository.findAll();
            // Se la keyword è vuota oppure è null, allora prendi una index normale
        }
        //model.addAttribute("username", authentication.getName());
        model.addAttribute("pizze", Pizza);
        return "pizze/index";
    }

    @Autowired 
    private IngredientRepository ingredientRepository;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pizza with id " + id);
        model.addAttribute("Pizza", pizzaRepository.findById(id).get());
        return "pizze/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        // Per ottenere tutte le categorie necessarie alla selezione sulla creazione di una nuova pizza, bisogna prendere le informazioni dal DB sulla base di quanti ingredienti sono presenti nella Repository degli ingredienti stessi, come fatto qui sotto:
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/create";
        }

        pizzaRepository.save(formPizza);

        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pizza with id " + id);

        model.addAttribute("pizza", pizzaOptional.get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/edit";
        }

        pizzaRepository.save(formPizza);

        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {

        pizzaRepository.deleteById(id);

        return "redirect:/pizze";
    }

    @GetMapping("/offers/{id}")
    public String borrow(@PathVariable("id") Integer id, Model model) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is no pizza with id " + id + ", so you cannot create an offer for it!");

        model.addAttribute("pizza", pizzaOptional.get());
        Offers offers = new Offers();
        offers.setPizza(pizzaOptional.get());
        model.addAttribute("offers", offers);
        
        return "offers/create";
    }


}
