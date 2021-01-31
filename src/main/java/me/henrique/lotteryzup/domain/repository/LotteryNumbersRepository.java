package me.henrique.lotteryzup.domain.repository;

import me.henrique.lotteryzup.domain.entity.LotteryNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryNumbersRepository extends JpaRepository<LotteryNumbers, Long> {
    List<LotteryNumbers> findByPersonEmail(String email);
}
