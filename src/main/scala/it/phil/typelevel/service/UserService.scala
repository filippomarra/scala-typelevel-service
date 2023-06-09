package it.phil.typelevel.service

import cats.effect.IO

import it.phil.typelevel.model.User

trait UserService {
  def getAll(): IO[List[User]]
  def getUserById(id: Long): IO[Option[User]]
}
