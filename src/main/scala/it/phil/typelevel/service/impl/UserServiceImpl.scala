package it.phil.typelevel.service.impl

import cats.effect.IO
import com.typesafe.scalalogging.Logger
import doobie.implicits._
import doobie.util.transactor.Transactor
import org.http4s.client.dsl.Http4sClientDsl

import it.phil.typelevel.model.User
import it.phil.typelevel.repository.UserRepository
import it.phil.typelevel.service.UserService

class UserServiceImpl(userRepository: UserRepository, dbTransactor: Transactor[IO]) extends UserService {

  val logger = Logger(getClass.getName)
  
  override def getAll(): IO[List[User]] = {
    for {
      _ <- IO(logger.info("Fetching all users..."))
      users <- userRepository.getAll().transact(dbTransactor)
      _ <- IO(logger.info(s"Retrieved ${users.size} users."))
    } yield users
  }
}
