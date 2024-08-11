package me.dio.dev_week_2024.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity(name = "tb_card")
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(unique = true)
    var number: String = ""

    @Column(name = "available_limit", precision = 13, scale = 2)
    var limit: BigDecimal = BigDecimal.ZERO
}