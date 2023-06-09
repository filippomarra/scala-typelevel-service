package it.phil.typelevel.repository

import doobie.free.connection.ConnectionIO

import it.phil.typelevel.model.User

trait UserRepository {
  def getAll(): ConnectionIO[List[User]]
}
