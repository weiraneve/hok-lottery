package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Int> {

    fun findByEncryptCode(encryptCode: String): Team
}
