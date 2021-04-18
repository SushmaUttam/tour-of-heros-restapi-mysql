package com.tourofheroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
