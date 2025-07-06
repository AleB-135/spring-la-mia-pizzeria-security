package org.lessons.java.spring.crud.pizzeria_crud.repository;

import org.lessons.java.spring.crud.pizzeria_crud.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersRepository extends JpaRepository<Offers, Integer> {

}
