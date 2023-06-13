package it.phil.typelevel.service

import cats.effect.IO

import it.phil.typelevel.model.User

trait UserService {
  def getAll(): IO[List[User]]
  def getById(id: Long): IO[Option[User]]
  def create(user: User): IO[Int]
}
