package com.company.data.service.impl;

import static com.company.data.repository.PersonSpecification.applyFilters;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.data.domain.Person;
import com.company.data.repository.PersonRepository;
import com.company.data.service.PersonService;
import com.company.data.service.dto.PersonDTO;
import com.company.data.service.mapper.PersonMapper;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable, PersonDTO person) {
        log.debug("Request to get all People");
        
        return personRepository.findAll(where(applyFilters(person)), pageable).map(personMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id)
            .map(personMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
}
