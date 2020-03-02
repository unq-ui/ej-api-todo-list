package todo

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main() = MainApp().start()

class MainApp {

    private var todoController: TodoController = TodoController(Bootstrap().getUsers());

    fun start() {
        val app = Javalin.create {
            it.enableCorsForAllOrigins()
        }
        app.start(7000)

        app.routes {
            path("/:userid/todos") {
                get(todoController::getAll)
                post(todoController::addNewTodo)
                path(":id") {
                    put(todoController::updateTodo)
                    delete(todoController::deleteTodo)
                }
            }
        }
        app.post("login", todoController::login)
        app.post("register", todoController::register)
        app.get("all", todoController::all)
    }
}
