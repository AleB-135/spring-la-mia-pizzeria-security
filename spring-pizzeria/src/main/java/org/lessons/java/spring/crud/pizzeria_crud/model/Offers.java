package org.lessons.java.spring.crud.pizzeria_crud.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "You must specify a starting date for the special offer")
    @FutureOrPresent(message = "You cannot start a special offer in the past")
    private LocalDate startingDate;

    @NotNull(message = "You must specify a finishing date for the special offer")
    @FutureOrPresent(message = "You cannot finish a special offer in the past")
    private LocalDate finishingDate;

    @NotEmpty(message = "You must specify a title for the special offer")
    private String title;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    //In JoinColumn il nome corrisponde alla Foreign Key del database (da qui il nome pizza_id)
    private Pizza pizza;

    
    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinishingDate() {
        return this.finishingDate;
    }

    public void setFinishingDate(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
