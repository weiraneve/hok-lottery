package com.weiran.lottery.repository

import com.weiran.lottery.entity.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : JpaRepository<Log, Int> {

    @Query("SELECT * FROM log l WHERE l.team_id = ?1 ORDER BY l.time DESC ", nativeQuery=true)
    fun findByTeamId(teamId: Int): List<Log>

}
