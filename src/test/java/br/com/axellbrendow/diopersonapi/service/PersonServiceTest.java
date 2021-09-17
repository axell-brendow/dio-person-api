package br.com.axellbrendow.diopersonapi.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.axellbrendow.diopersonapi.dto.request.PersonDTO;
import br.com.axellbrendow.diopersonapi.dto.request.PhoneDTO;
import br.com.axellbrendow.diopersonapi.dto.response.MessageResponseDTO;
import br.com.axellbrendow.diopersonapi.entity.Person;
import br.com.axellbrendow.diopersonapi.entity.Phone;
import br.com.axellbrendow.diopersonapi.enums.PhoneType;
import br.com.axellbrendow.diopersonapi.mapper.PersonMapper;
import br.com.axellbrendow.diopersonapi.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private PersonMapper mapper = PersonMapper.INSTANCE;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        var personDTO = PersonDTO
            .builder()
            .firstName("first")
            .lastName("last")
            .cpf("23108762087")
            .birthDate("15-09-2021")
            .phones(List.of(
                PhoneDTO.builder().number("(11)999999999").type(PhoneType.MOBILE).build()
            ))
            .build();

        var entity = mapper.toModel(personDTO);

        var savedEntity = Person
            .builder()
            .id(1L)
            .firstName("first")
            .lastName("last")
            .cpf("23108762087")
            .birthDate(LocalDate.parse("2021-09-15"))
            .phones(List.of(
                Phone.builder().number("(11)999999999").type(PhoneType.MOBILE).build()
            ))
            .build();

        Mockito.when(repository.save(entity)).thenReturn(savedEntity);

        var res = service.create(personDTO);

        var expectedRes = MessageResponseDTO
            .builder()
            .message("Created person with ID " + savedEntity.getId())
            .build();

        Assertions.assertEquals(expectedRes, res);
    }
}
