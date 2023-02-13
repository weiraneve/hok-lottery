package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.mapper.HeroRepository
import com.weiran.lottery.mapper.TeamRepository
import org.springframework.stereotype.Service

interface ClearService {
    fun refreshOneTeam(teamId: Int): MyResult
    fun refreshAllTeam(): MyResult
    fun refreshAllHero(): MyResult

}

@Service
class ClearServiceImpl(
    val teamRepository: TeamRepository,
    val heroRepository: HeroRepository
) : ClearService {

    override fun refreshOneTeam(teamId: Int): MyResult {
        val result = MyResult()
        if (teamRepository.findById(teamId).isEmpty) {
            result.data = NOT_FOUND_TEAM
        } else {
            teamRepository.clearOneTeam(teamId)
            result.data = "刷新队伍${teamId}成功"
        }
        teamRepository.clearOneTeam(teamId)
        return result
    }

    override fun refreshAllTeam(): MyResult {
        val result = MyResult()
        teamRepository.clearAllTeam()
        heroRepository.clearAllHero()
        result.data = REFRESH_ALL_TEAM_SUCCESS
        return result
    }

    override fun refreshAllHero(): MyResult {
        val result = MyResult()
        heroRepository.clearAllHero()
        result.data = REFRESH_ALL_HERO_SUCCESS
        return result
    }

    companion object {
        private const val NOT_FOUND_TEAM = "未有查询到此队伍"
        private const val REFRESH_ALL_TEAM_SUCCESS = "刷新全部队伍成功"
        private const val REFRESH_ALL_HERO_SUCCESS = "刷新全部英雄成功"
    }

}
