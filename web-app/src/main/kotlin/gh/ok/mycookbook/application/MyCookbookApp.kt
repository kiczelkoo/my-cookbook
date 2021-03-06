package gh.ok.mycookbook.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MyCookbookApp

fun main(args: Array<String>) {
	runApplication<MyCookbookApp>(*args)
}
