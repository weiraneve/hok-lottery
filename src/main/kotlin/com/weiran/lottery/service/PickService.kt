package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.entity.Log
import com.weiran.lottery.mapper.HeroRepository
import com.weiran.lottery.mapper.LogRepository
import com.weiran.lottery.mapper.TeamRepository
import org.springframework.stereotype.Service

@Service
class PickService(
    val heroRepository: HeroRepository,
    val logRepository: LogRepository,
    val teamRepository: TeamRepository
) {
    fun pick(encryptCode: String): MyResult {
        val team = teamRepository.findByEncryptCode(encryptCode)
        val myResult = MyResult()
        if (team.isPick) {
            myResult.data = team.pickContent
        } else {
            val pickResult = heroPick()
            myResult.data = pickResult
            saveResultForLog(team.id, pickResult)
        }

        return myResult
    }

    private fun saveResultForLog(teamId: Int?, pickResult: String) {
        val log = Log()
        log.teamId = teamId
        log.pickGroup = pickResult
        logRepository.save(log)
    }

    private fun heroPick(): String {
        var heroGroupName = ""
        (1..5).shuffled().take(2).forEach {
            val heroes = heroRepository.findHeroesByLine(it)
            // todo
            heroGroupName += "[" + heroes.shuffled().take(1)[0].name + "]"
        }
        return heroGroupName
    }

}
