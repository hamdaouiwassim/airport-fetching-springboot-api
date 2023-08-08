package com.airportFetching.airportfetching.dao;

import com.airportFetching.airportfetching.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenDAO extends JpaRepository<Screen, Long> {
    Screen save(Screen screen);
}
