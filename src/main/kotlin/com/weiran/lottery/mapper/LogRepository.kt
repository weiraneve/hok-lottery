package com.weiran.lottery.mapper

import com.weiran.lottery.entity.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : JpaRepository<Log, Int> {
}
