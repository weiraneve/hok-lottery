package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Team
import org.springframework.data.repository.Repository

interface TeamRepository : Repository<Team, Int> {

    fun findByEncryptCode(encryptCode: String): Team
}
