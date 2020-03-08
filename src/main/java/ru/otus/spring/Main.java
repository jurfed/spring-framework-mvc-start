package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableMapRepositories
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Person(0,"Pushkin"));
        repository.save(new Person(1, "Kuzya"));
        repository.save(new Person(2, "Mashenka"));
    }
}