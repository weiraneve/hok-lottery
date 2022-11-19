package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Hero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface HeroRepository : JpaRepository<Hero, Int> {
    @Query("SELECT h FROM Hero h WHERE h.line = ?1 AND h.isPick = FALSE ")
    fun getHeroesByRule(line: Int): List<Hero>

}
