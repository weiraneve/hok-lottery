package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Log
import org.springframework.data.repository.Repository

interface LogRepository : Repository<Log, Int> {
    fun save(log : Log)
}
