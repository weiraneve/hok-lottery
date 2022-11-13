package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.entity.Hero
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
        when (team.pickCount) {
            0 -> {
                val pickResult = heroPick(heroRepository)
                myResult.data = pickResult
                saveOneResultForLog(team.id, logRepository, pickResult)
            }

            1 -> {
                val pickResult = heroPick(heroRepository)
                myResult.data = pickResult
                saveTwoResultForLog(team.id, logRepository, pickResult)
            }

            2 -> {
                myResult.data = NO_CHANCE
            }
        }
        return myResult
    }

    private fun saveOneResultForLog(teamId: Int?, logRepository: LogRepository, pickResult: String) {
        val log = Log()
        log.teamId = teamId
        log.pickGroupOne = pickResult
        logRepository.save(log)
    }

    private fun saveTwoResultForLog(teamId: Int?, logRepository: LogRepository, pickResult: String) {
        val log = Log()
        log.teamId = teamId
        log.pickGroupTwo = pickResult
        logRepository.save(log)
    }

    private fun heroPick(heroRepository: HeroRepository): String {
        var heroGroupName = ""
        randomList.shuffled().take(2).forEach {
            val heroes = heroRepository.findHeroesByLine(it)

            // todo
            heroGroupName += "[" + heroes.shuffled().take(1)[0].name + "]"
        }
        return "1+2"
    }

    companion object {
        private const val NO_CHANCE = "sorry your team only has two chance to pick"
        private val randomList = arrayListOf(1, 2, 3, 4, 5)
    }

}
