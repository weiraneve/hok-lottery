package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.repository.HeroRepository
import com.weiran.lottery.repository.TeamRepository
import org.springframework.stereotype.Service

interface ResetService {
    fun resetOneTeam(teamId: Int): MyResult
    fun resetAllTeam(): MyResult
    fun resetAllHero(): MyResult

}

@Service
class ResetServiceImpl(
    val teamRepository: TeamRepository,
    val heroRepository: HeroRepository
) : ResetService {

    override fun resetOneTeam(teamId: Int): MyResult {
        val result = MyResult()
        if (teamRepository.findById(teamId).isEmpty) {
            result.data = NOT_FOUND_TEAM
        } else {
            teamRepository.resetOneTeam(teamId)
            result.data = "刷新队伍${teamId}成功"
        }
        return result
    }

    override fun resetAllTeam(): MyResult {
        val result = MyResult()
        teamRepository.resetAllTeam()
        heroRepository.resetAllHero()
        result.data = RESET_ALL_TEAM_SUCCESS
        return result
    }

    override fun resetAllHero(): MyResult {
        val result = MyResult()
        heroRepository.resetAllHero()
        result.data = RESET_ALL_HERO_SUCCESS
        return result
    }

    companion object {
        private const val NOT_FOUND_TEAM = "未有查询到此队伍"
        private const val RESET_ALL_TEAM_SUCCESS = "重置所有队伍成功"
        private const val RESET_ALL_HERO_SUCCESS = "重置所有英雄成功"
    }

}
