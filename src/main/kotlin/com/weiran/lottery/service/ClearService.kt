package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.repository.HeroRepository
import com.weiran.lottery.repository.TeamRepository
import org.springframework.stereotype.Service

interface ClearService {
    fun clearOneTeam(teamId: Int): MyResult
    fun clearAllTeam(): MyResult
    fun clearAllHero(): MyResult

}

@Service
class ClearServiceImpl(
    val teamRepository: TeamRepository,
    val heroRepository: HeroRepository
) : ClearService {

    override fun clearOneTeam(teamId: Int): MyResult {
        val result = MyResult()
        if (teamRepository.findById(teamId).isEmpty) {
            result.data = NOT_FOUND_TEAM
        } else {
            teamRepository.clearOneTeam(teamId)
            result.data = "刷新队伍${teamId}成功"
        }
        return result
    }

    override fun clearAllTeam(): MyResult {
        val result = MyResult()
        teamRepository.clearAllTeam()
        heroRepository.clearAllHero()
        result.data = CLEAR_ALL_TEAM_SUCCESS
        return result
    }

    override fun clearAllHero(): MyResult {
        val result = MyResult()
        heroRepository.clearAllHero()
        result.data = CLEAR_ALL_HERO_SUCCESS
        return result
    }

    companion object {
        private const val NOT_FOUND_TEAM = "未有查询到此队伍"
        private const val CLEAR_ALL_TEAM_SUCCESS = "重置所有队伍成功"
        private const val CLEAR_ALL_HERO_SUCCESS = "重置所有英雄成功"
    }

}
