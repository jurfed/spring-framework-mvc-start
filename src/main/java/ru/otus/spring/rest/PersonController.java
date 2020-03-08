package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    /**
     * get all persons by Get request
     * http://localhost:8080/person
     *
     * @return
     */
    @RequestMapping(
            value = "/person",
            method = RequestMethod.GET
    )
    public List<PersonDto> get() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }


    /**
     * get person by param id in get - request
     * http://localhost:8080/personId?id=1
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(
            value = "/personId",
            method = RequestMethod.GET
    )
    public PersonDto get(@RequestParam(value = "id") int id, Model model) {
        Person personDto = repository.findById(id).get();
        return PersonDto.toDto(personDto);
    }


    /**
     * get person by name in address in get - request
     * http://localhost:8080/personName/Mashenka
     *
     * @param name
     * @return
     */
    @GetMapping("/personName/{name}")
//    @RequestMapping("/personName/{name}")
    public PersonDto getByName(@PathVariable String name) {
        Person personDto = repository.findByName(name).get();
        return PersonDto.toDto(personDto);

    }

    /**
     * 1) receive json in post - request from postman (@RequestBody) and it to the repository
     * 2) return list of person to the postman (@ResponseBody)
     * Poatman -> http://localhost:8080/create -> POST -> raw -> JSON -> {"id" :"4","name": "Ivan Ivanich"}
     * @param person
     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = "application/xml")
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Person> savePerson(@RequestBody Person person) {
        repository.save(person);
        List<Person> all = repository.findAll();
        all.forEach(person1 -> System.err.println(person1));
        return all;
    }

    /**
     * ResponseEntity example
     * ResponseEntity.ok - successful code
     * "Ok" - body
     * http://localhost:8080/ping
     * @return
     */
    @RequestMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("OK");
    }

}
