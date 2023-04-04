package com.weiran.lottery.service

import com.weiran.lottery.common.LogResponse
import com.weiran.lottery.common.MyResult
import com.weiran.lottery.common.PostParam
import com.weiran.lottery.entity.Hero
import com.weiran.lottery.entity.Log
import com.weiran.lottery.entity.Team
import com.weiran.lottery.repository.HeroRepository
import com.weiran.lottery.repository.LogRepository
import com.weiran.lottery.repository.TeamRepository
import org.springframework.stereotype.Service
import java.util.*


interface PickService {
    fun pick(param: PostParam): MyResult
}

@Service
class PickServiceImpl(
    val heroRepository: HeroRepository,
    val logRepository: LogRepository,
    val teamRepository: TeamRepository
) : PickService {

    override fun pick(param: PostParam): MyResult {
        val team = param.encryptCode?.let { teamRepository.findByEncryptCode(it) }
        val result = MyResult()
        var logResponses: List<LogResponse> = listOf()
        team?.let {
            checkTeamIsPicked(it, result)
            logResponses = getLogs(it.id!!)
        }
        result.apply {
            teamId = team?.id
            time = team?.updateTime ?: Date()
            logs = logResponses
        }
        return result
    }

    private fun getLogs(teamId: Int): List<LogResponse> {
        val logs = logRepository.findByTeamId(teamId)
        return logs.map { log ->
            LogResponse(
                teamId = log.teamId,
                pickGroup = log.pickGroup,
                time = log.time
            )
        }
    }

    private fun checkTeamIsPicked(team: Team, result: MyResult) {
        if (team.isPicked) {
            result.data = team.pickContent
        } else {
            val pickResult = heroPick() + "or" + heroPick()
            result.data = pickResult
            saveResultForLog(team.id, pickResult)
            updateTeamIsPicked(team, pickResult)
        }
    }

    private fun updateTeamIsPicked(team: Team, pickResult: String) {
        team.apply {
            isPicked = true
            pickContent = pickResult
            updateTime = Date()
        }
        teamRepository.save(team)
    }

    private fun saveResultForLog(teamIndex: Int?, pickResult: String) {
        val log = Log(
            teamId = teamIndex,
            pickGroup = pickResult,
            time = Date()
        )
        logRepository.save(log)
    }

    private fun heroPick(): String {
        return getSecondRandomHero(getFirstRandomHero())
    }

    private fun getFirstRandomHero(): Hero {
        val hero = heroRepository.getHeroesNotIsPick()[0]
        saveHeroAndIsPick(hero)
        return hero
    }

    private fun getSecondRandomHero(existHero: Hero): String {
        val hero = heroRepository.getHeroesNotIsPick()[0]
        saveHeroAndIsPick(hero)
        return "[${existHero.name}][${hero.name}]"
    }

    private fun saveHeroAndIsPick(hero: Hero) {
        hero.isPick = true
        heroRepository.save(hero)
    }

}
