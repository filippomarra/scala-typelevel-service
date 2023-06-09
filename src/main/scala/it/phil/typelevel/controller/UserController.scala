package it.phil.typelevel.controller

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.dsl.Http4sDsl

import it.phil.typelevel.service.UserService

object UserController {

  def routes(userService: UserService): HttpRoutes[IO] = {
    val dsl = new Http4sDsl[IO] {}
    import dsl._
    HttpRoutes.of[IO] {

      case GET -> Root / "health" => Ok("UserController is ready!")

      case GET -> Root / "users" => 
        for {
          users <- userService.getAll()
          response <- Ok(users)
        } yield response
      
      case GET -> Root / "users" / LongVar(id) =>
        for {
          user <- userService.getUserById(id)
          response <- user match {
            case Some(u) => Ok(u)
            case None => NotFound()
          }
        } yield response
    
    }
  }

}
