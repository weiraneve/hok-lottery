package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Hero
import org.springframework.data.repository.Repository

interface HeroRepository : Repository<Hero, Int> {

    fun save(hero: Hero)

    fun findHeroById(id: Int): Hero

    fun findHeroesByLine(line: Int): List<Hero>

}
