package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.common.PostParam
import com.weiran.lottery.entity.Log
import com.weiran.lottery.entity.Team
import com.weiran.lottery.mapper.HeroRepository
import com.weiran.lottery.mapper.LogRepository
import com.weiran.lottery.mapper.TeamRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class PickService {

    @Autowired
    private lateinit var heroRepository: HeroRepository
    @Autowired
    private lateinit var logRepository: LogRepository
    @Autowired
    private lateinit var teamRepository: TeamRepository

    fun pick(param: PostParam): MyResult {
        val team = param.encryptCode?.let { teamRepository.findByEncryptCode(it) }
        val myResult = MyResult()
        if (team != null) {
            if (team.isPicked) {
                myResult.data = team.pickContent
            } else {
                var pickResult = heroPick()
                pickResult += "or" + heroPick()
                myResult.data = pickResult
                saveResultForLog(team.id, pickResult)
                updateTeamIsPicked(team, pickResult)
            }
        }
        myResult.apply {
            teamId = team?.id
            time = team?.updateTime ?: Date()
        }

        return myResult
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
        var heroGroupName = ""
        (1..5).shuffled().take(2).forEach {
            val heroes = heroRepository.getHeroesByRule(it)
            heroes.shuffled().take(1).forEach { hero ->
                heroGroupName += "[" + hero.name + "]"
                hero.isPick = true
                heroRepository.save(hero)
            }
        }
        return heroGroupName
    }

}
