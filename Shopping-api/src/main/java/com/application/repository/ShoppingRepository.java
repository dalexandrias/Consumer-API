package com.application.repository;

import com.application.model.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long>, ReportRepository {

    public List<Shopping> findAllByUserIdentifier(String userIdentifier);

    public List<Shopping> findAllByTotalGreaterThan(Float total);

    List<Shopping> findAllByDateGreaterThanEqual(Date date);
}
