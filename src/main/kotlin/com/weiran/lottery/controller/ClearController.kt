package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.service.ClearService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ClearController(val clearService: ClearService) {


    @GetMapping(value = ["/clear"], produces = ["application/json; charset=utf-8"])
    fun clearTeam(@RequestParam id: Int): MyResult {
        return clearService.clearOneTeam(id)
    }

    @GetMapping(value = ["/clear/team"], produces = ["application/json; charset=utf-8"])
    fun clearAllTeam(): MyResult {
        return clearService.clearAllTeam()
    }

    @GetMapping(value = ["/clear/hero"], produces = ["application/json; charset=utf-8"])
    fun clearAllHero(): MyResult {
        return clearService.clearAllHero()
    }

}
