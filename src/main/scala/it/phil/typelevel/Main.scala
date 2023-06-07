package it.phil.typelevel

import java.util.concurrent.Executors

import cats.effect.{ExitCode, IO, IOApp}
import doobie.util.transactor.Transactor
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import scala.concurrent.ExecutionContext

import it.phil.typelevel.controller.UserController
import it.phil.typelevel.repository.UserRepository
import it.phil.typelevel.repository.impl.UserRepositoryImpl
import it.phil.typelevel.service.UserService
import it.phil.typelevel.service.impl.UserServiceImpl
import it.phil.typelevel.utils.DatabaseIO

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {

    val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1)) // handle async requests

    val userRepository: UserRepository = new UserRepositoryImpl()
    val dbTransactor: Transactor[IO] = DatabaseIO.transactor
    val userService: UserService = new UserServiceImpl(userRepository, dbTransactor)
    val httpApp = UserController.routes(userService).orNotFound

    BlazeServerBuilder[IO](ec)
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(httpApp)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }

}

