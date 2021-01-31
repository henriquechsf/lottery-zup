package me.henrique.lotteryzup.domain.repository;

import me.henrique.lotteryzup.domain.entity.PlayNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NumberRepository extends JpaRepository<PlayNumbers, Long> {
    List<PlayNumbers> findByPersonEmail(String email);
}
