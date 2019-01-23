package pl.kamil.library.reservations

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class ReservationsApplication

fun main(args: Array<String>) {
	runApplication<ReservationsApplication>(*args)
}

@Configuration
class WebConfig {
	@Bean
	fun restTemplate() = RestTemplate()
}

