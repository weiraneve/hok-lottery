package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.service.ResetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ResetController(val resetService: ResetService) {


    @GetMapping(value = ["/reset/team"], produces = ["application/json; charset=utf-8"])
    fun resetTeam(@RequestParam id: Int): MyResult {
        return resetService.resetOneTeam(id)
    }

    @GetMapping(value = ["/reset/teams"], produces = ["application/json; charset=utf-8"])
    fun resetAllTeam(): MyResult {
        return resetService.resetAllTeam()
    }

    @GetMapping(value = ["/reset/heroes"], produces = ["application/json; charset=utf-8"])
    fun resetAllHero(): MyResult {
        return resetService.resetAllHero()
    }

}
