package me.henrique.lotteryzup.domain.service.impl;

import me.henrique.lotteryzup.domain.entity.LotteryNumbers;
import me.henrique.lotteryzup.domain.entity.Person;
import me.henrique.lotteryzup.domain.repository.LotteryNumbersRepository;
import me.henrique.lotteryzup.domain.repository.PersonRepository;
import me.henrique.lotteryzup.domain.service.LotteryNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryNumbersServiceImpl implements LotteryNumbersService {

    private PersonRepository personRepository;
    private LotteryNumbersRepository numbersRepository;

    public LotteryNumbersServiceImpl(LotteryNumbersRepository numbersRepository, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.numbersRepository = numbersRepository;
    }

    @Override
    @Transactional
    public LotteryNumbers generateNumbers(Integer quantity, String email) {
        Optional<Person> obj = personRepository.findByEmail(email);

        Person person = new Person();
        if (obj.isPresent()) {
            person = obj.get();
        } else {
            person.setEmail(email);
            personRepository.save(person);
        }

        LotteryNumbers playNumbers = new LotteryNumbers(null, randomNumbers(quantity), person);
        numbersRepository.save(playNumbers);

        return playNumbers;
    }

    @Override
    public List<LotteryNumbers> getByEmail(String email) {
        return numbersRepository.findByPersonEmail(email);
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
