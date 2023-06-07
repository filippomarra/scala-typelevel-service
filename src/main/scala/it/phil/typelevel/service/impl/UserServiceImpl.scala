package it.phil.typelevel.service.impl

import cats.effect.IO
import doobie.implicits._
import doobie.util.transactor.Transactor
import org.http4s.client.dsl.Http4sClientDsl

import it.phil.typelevel.repository.UserRepository
import it.phil.typelevel.service.UserService

class UserServiceImpl(userRepository: UserRepository, dbTransactor: Transactor[IO]) extends UserService {

}
