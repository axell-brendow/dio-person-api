package br.com.axellbrendow.diopersonapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.axellbrendow.diopersonapi.dto.request.PersonDTO;
import br.com.axellbrendow.diopersonapi.dto.response.MessageResponseDTO;
import br.com.axellbrendow.diopersonapi.exception.NotFoundException;
import br.com.axellbrendow.diopersonapi.mapper.PersonMapper;
import br.com.axellbrendow.diopersonapi.repository.PersonRepository;

@Service
public class PersonService {
    private PersonRepository repository;
    private PersonMapper mapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO create(PersonDTO personDTO) {
        var savedPerson = repository.save(mapper.toModel(personDTO));
        return MessageResponseDTO
            .builder()
            .message("Created person with ID " + savedPerson.getId())
            .build();
    }

    public List<PersonDTO> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return mapper.toDto(entity);
    }

    public void deleteById(Long id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(id));

        var person = mapper.toModel(personDTO);
        person.setId(id);

        repository.save(person);

        return MessageResponseDTO
            .builder()
            .message("Updated person with ID " + id)
            .build();
    }
}
