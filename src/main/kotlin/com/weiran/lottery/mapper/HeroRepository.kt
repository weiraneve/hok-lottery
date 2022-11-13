package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Hero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface HeroRepository : JpaRepository<Hero, Int> {
    @Query("select h FROM Hero h where h.line = ?1 and h.isPick = false ")
    fun getHeroesByRule(line: Int): List<Hero>

}
