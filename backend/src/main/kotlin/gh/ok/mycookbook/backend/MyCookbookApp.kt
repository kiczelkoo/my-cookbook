package gh.ok.mycookbook.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyCookbookApp

fun main(args: Array<String>) {
	runApplication<MyCookbookApp>(*args)
}
