package it.phil.typelevel.controller

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.dsl.Http4sDsl

import it.phil.typelevel.model.User
import it.phil.typelevel.service.UserService
import it.phil.typelevel.utils.HttpResponse

object UserController {

  def routes(userService: UserService): HttpRoutes[IO] = {
    val dsl = new Http4sDsl[IO] {}
    import dsl._
    HttpRoutes.of[IO] {

      case GET -> Root / "health" => 
        Ok(HttpResponse("UserController is ready!"))

      case GET -> Root / "users" => 
        for {
          users    <- userService.getAll()
          response <- Ok(users)
        } yield response
      
      case GET -> Root / "users" / LongVar(id) =>
        for {
          user     <- userService.getById(id)
          response <- user match {
            case Some(u) => Ok(u)
            case None => NotFound(HttpResponse("User not found."))
          }
        } yield response

      case req @ POST -> Root / "users" =>
        for {
          user   <- req.as[User]
          result <- userService.create(user)
          response <- result match {
            case 1 => Ok(HttpResponse("User successfully created."))
            case _ => InternalServerError(HttpResponse("User not created."))
          }
        } yield response

    }
  }

}
