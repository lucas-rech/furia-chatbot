package com.lucasrech.furiaapi.repository;

import com.lucasrech.furiaapi.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    QuoteEntity findByShortcut(String shortcut);

    @Query(value = "SELECT * FROM quotes WHERE shortcut IS NOT NULL", nativeQuery = true)
    List<QuoteEntity> findAllQuotesWithShortcut();
}
