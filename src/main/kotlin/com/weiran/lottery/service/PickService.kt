package com.weiran.lottery.service

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.entity.Log
import com.weiran.lottery.mapper.HeroRepository
import com.weiran.lottery.mapper.LogRepository
import com.weiran.lottery.mapper.TeamRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PickService {

    @Autowired
    private lateinit var heroRepository: HeroRepository
    @Autowired
    private lateinit var logRepository: LogRepository
    @Autowired
    private lateinit var teamRepository: TeamRepository

    fun pick(encryptCode: String): MyResult {
        val team = teamRepository.findByEncryptCode(encryptCode)
        val myResult = MyResult()
        if (team.isPicked) {
            myResult.data = team.pickContent
        } else {
            var pickResult = heroPick()
            pickResult += "+" + heroPick()
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
            heroGroupName += "[" + heroes.shuffled().take(1)[0].name + "]"
        }
        return heroGroupName
    }

}
