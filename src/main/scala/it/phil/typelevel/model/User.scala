package it.phil.typelevel.model

case class User(
  id: Long, 
  name: String, 
  surname: String, 
  email: Option[String]
)
