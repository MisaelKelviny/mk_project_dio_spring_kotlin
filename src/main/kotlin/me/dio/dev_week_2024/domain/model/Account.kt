package me.dio.dev_week_2024.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity(name = "tb_account")
class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(unique = true)
    var number: String = ""

    var agency: String = ""

    @Column(precision = 13, scale = 2)
    var balance: BigDecimal = BigDecimal.ZERO

    @Column(name = "additional_limit", precision = 13, scale = 2)
    var limit: BigDecimal = BigDecimal.ZERO
}