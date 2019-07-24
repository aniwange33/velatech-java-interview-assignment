package com.amos.velatechjavainterviewassigment.repository;

import com.amos.velatechjavainterviewassigment.model.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailRepository extends JpaRepository<CardDetail,Long> {
    CardDetail findByIin(Long iin);
}
