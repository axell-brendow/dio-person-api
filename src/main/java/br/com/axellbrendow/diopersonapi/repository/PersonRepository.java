package br.com.axellbrendow.diopersonapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.axellbrendow.diopersonapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    //
}
