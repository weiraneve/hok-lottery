package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.service.ClearService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ClearController {

    @Autowired
    private lateinit var clearService: ClearService

    @GetMapping("/refresh")
    fun clearTeam(@RequestParam id: Int): MyResult {
        return clearService.refreshOneTeam(id)
    }

    @GetMapping("/refresh/team")
    fun clearAllTeam(): MyResult {
        return clearService.refreshAllTeam()
    }

    @GetMapping("/refresh/hero")
    fun clearAllHero(): MyResult {
        return clearService.refreshAllHero()
    }

}
