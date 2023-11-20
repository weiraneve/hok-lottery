package com.weiran.lottery.repository

import com.weiran.lottery.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface TeamRepository : JpaRepository<Team, Int> {

    fun findByEncryptCode(encryptCode: String): Team?

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Team SET pickContent='',isPicked=0,updateTime=current_time WHERE id=?1 ")
    fun resetOneTeam(id: Int)

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Team SET pickContent='',isPicked=0,updateTime=current_time WHERE isPicked=true ")
    fun resetAllTeam()

}
