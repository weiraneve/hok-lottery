package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Hero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HeroRepository : JpaRepository<Hero, Int> {
    fun findHeroesByLine(line: Int): List<Hero>

}
