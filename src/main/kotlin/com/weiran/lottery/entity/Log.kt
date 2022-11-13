package com.weiran.lottery.entity

import javax.persistence.*

@Entity
@Table(name = "log")
class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "team_id")
    var teamId: Int? = null

    @Column(name = "pick_group")
    var pickGroup: String = ""

}
