package it.phil.typelevel.repository.impl

import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.postgres.implicits._

import it.phil.typelevel.model.User
import it.phil.typelevel.repository.UserRepository

class UserRepositoryImpl extends UserRepository {

  override def getAll(): ConnectionIO[List[User]] = {
    sql"SELECT * FROM users".query[User].to[List]
  }

  override def getUserById(id: Long): ConnectionIO[Option[User]] = {
    sql"SELECT * FROM users WHERE id = $id".query[User].option
  }

}
