package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Hero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface HeroRepository : JpaRepository<Hero, Int> {
    @Query("SELECT h FROM Hero h WHERE h.line = ?1 AND h.isPick = FALSE ")
    fun getHeroesByRule(line: Int): List<Hero>

    @Query("SELECT * FROM hero h WHERE h.is_pick = FALSE ORDER BY RAND() LIMIT 1", nativeQuery=true)
    fun getHeroesNotIsPick(): List<Hero>

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Hero SET isPick=false WHERE isPick=true ")
    fun clearAllHero()

}
