package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.entity.Team
import com.weiran.lottery.mapper.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClearService {

    @Autowired
    private lateinit var teamRepository: TeamRepository

    fun clearOne(teamId: Int): MyResult {
        val result = MyResult()
        val team: Team
        try {
            team = teamRepository.findById(teamId).get()
            team.pickContent = ""
            team.isPicked = false
            team.updateTime = Date()
            teamRepository.save(team)
            result.data = "清除队伍${team.id}成功"
        } catch (e: Exception) {
            result.data = "未有查询到此队伍"
        }
        return result
    }

    fun clearAll(): MyResult {
        val result = MyResult()
        teamRepository.clearAllTeam()
        result.data = "清除全部队伍成功"
        return result
    }

}
