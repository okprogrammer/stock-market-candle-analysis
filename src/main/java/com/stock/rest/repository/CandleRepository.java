package com.stock.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entity.Candle;

/**
 * The Interface CandleRepository.
 */
public interface CandleRepository extends JpaRepository<Candle, String> {

}
