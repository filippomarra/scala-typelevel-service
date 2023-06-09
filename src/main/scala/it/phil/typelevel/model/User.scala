package it.phil.typelevel.model

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe._
import org.http4s.{EntityDecoder, EntityEncoder}

case class User(
  id: Long, 
  name: String, 
  surname: String, 
  email: Option[String]
)

object User {
  implicit val decoder: Decoder[User] = deriveDecoder[User]
  implicit val encoder: Encoder[User] = deriveEncoder[User]
  implicit val entityDecoder: EntityDecoder[IO, User] = jsonOf[IO, User]
  implicit val entityEncoder: EntityEncoder[IO, User] = jsonEncoderOf[IO, User]
}

