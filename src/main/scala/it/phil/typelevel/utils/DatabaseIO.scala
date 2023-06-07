package it.phil.typelevel.utils

import cats.effect.{Blocker, IO}
import doobie.util.transactor.Transactor
import doobie.util.ExecutionContexts

import it.phil.typelevel.config.DatabaseConfig

object DatabaseIO {

  val config: DatabaseConfig = DatabaseConfig.config

  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  def transactor =
    Transactor.fromDriverManager[IO](
      config.driver,
      config.url,
      config.username,
      config.password,
      Blocker.liftExecutionContext(ExecutionContexts.synchronous)
    )

}
