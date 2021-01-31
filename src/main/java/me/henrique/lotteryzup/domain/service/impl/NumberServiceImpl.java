package me.henrique.lotteryzup.domain.service.impl;

import me.henrique.lotteryzup.domain.entity.Person;
import me.henrique.lotteryzup.domain.entity.PlayNumbers;
import me.henrique.lotteryzup.domain.repository.NumberRepository;
import me.henrique.lotteryzup.domain.repository.PersonRepository;
import me.henrique.lotteryzup.domain.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class NumberServiceImpl implements NumberService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    NumberRepository numberRepository;

    @Override
    @Transactional
    public PlayNumbers generateNumbers(Integer quantity, String email) {
        Optional<Person> obj = personRepository.findByEmail(email);

        Person person = new Person();
        if (obj.isPresent()) {
            person = obj.get();
        } else {
            person.setEmail(email);
            personRepository.save(person);
        }

        PlayNumbers playNumbers = new PlayNumbers(randomNumbers(quantity));
        playNumbers.setPerson(person);

        numberRepository.save(playNumbers);
        return playNumbers;
    }

    @Override
    public List<PlayNumbers> getByEmail(String email) {
        return numberRepository.findByPersonEmail(email);
    }

    private String randomNumbers(Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantidade de números de deve ser maior que 0");

        List<Integer> numbersList = new ArrayList<>();

        int min = 1;
        int max = 59;

        for (int i=0; i < quantity; i++) {
            int number = (int) (min + (Math.random() * (max - min)));
            while (numbersList.contains(number)) {
                number = (int) (min + (Math.random() * (max - min)));
            }
            numbersList.add(number);
        }
        numbersList.sort(Comparator.naturalOrder());
        return numbersList.toString();
    }
}
