package com.weiran.lottery.service

import com.weiran.lottery.common.LogResponse
import com.weiran.lottery.common.MyResult
import com.weiran.lottery.common.PostParam
import com.weiran.lottery.entity.Hero
import com.weiran.lottery.entity.Log
import com.weiran.lottery.entity.Team
import com.weiran.lottery.mapper.HeroRepository
import com.weiran.lottery.mapper.LogRepository
import com.weiran.lottery.mapper.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PickService {

    @Autowired
    private lateinit var heroRepository: HeroRepository

    @Autowired
    private lateinit var logRepository: LogRepository

    @Autowired
    private lateinit var teamRepository: TeamRepository

    fun pick(param: PostParam): MyResult {
        val team = param.encryptCode?.let { teamRepository.findByEncryptCode(it) }
        val result = MyResult()
        val logResponseList = arrayListOf<LogResponse>()
        if (team != null) {
            if (team.isPicked) {
                result.data = team.pickContent
            } else {
                var pickResult = heroPick()
                pickResult += "or" + heroPick()
                result.data = pickResult
                saveResultForLog(team.id, pickResult)
                updateTeamIsPicked(team, pickResult)
            }
            team.id?.let {
                val logs = logRepository.findByTeamId(it)
                if (logs != null) {
                    for (log in logs) {
                        val logResponse = LogResponse()
                        logResponse.apply {
                            teamId = log.teamId
                            pickGroup = log.pickGroup
                            time = log.time
                        }
                        logResponseList.add(logResponse)
                    }
                }
            }
        }

        result.apply {
            teamId = team?.id
            time = team?.updateTime ?: Date()
            logs = logResponseList
        }

        return result
    }

    private fun updateTeamIsPicked(team: Team, pickResult: String) {
        team.apply {
            isPicked = true
            pickContent = pickResult
            updateTime = Date()
        }
        teamRepository.save(team)
    }

    private fun saveResultForLog(team_id: Int?, pickResult: String) {
        val log = Log()
        val date = Date()
        log.apply {
            teamId = team_id
            pickGroup = pickResult
            time = date
        }
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
        var hero = heroRepository.getHeroesNotIsPick()[0]
        while (existHero.line == hero.line) {
            hero = heroRepository.getHeroesNotIsPick()[0]
        }
        saveHeroAndIsPick(hero)
        return "[${existHero.name}][${hero.name}]"
    }

    private fun saveHeroAndIsPick(hero: Hero) {
        hero.isPick = true
        heroRepository.save(hero)
    }

}
