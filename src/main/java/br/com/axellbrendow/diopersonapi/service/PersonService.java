package br.com.axellbrendow.diopersonapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.axellbrendow.diopersonapi.dto.response.MessageResponseDTO;
import br.com.axellbrendow.diopersonapi.entity.Person;
import br.com.axellbrendow.diopersonapi.repository.PersonRepository;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO create(Person person) {
        var savedPerson = repository.save(person);
        return MessageResponseDTO
            .builder()
            .message("Created person with ID " + savedPerson.getId())
            .build();
    }
}
