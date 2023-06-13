package it.phil.typelevel.utils

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe._
import org.http4s.{EntityDecoder, EntityEncoder}

case class HttpResponse(message: String)

object HttpResponse {
  implicit val decoder: Decoder[HttpResponse] = deriveDecoder[HttpResponse]
  implicit val encoder: Encoder[HttpResponse] = deriveEncoder[HttpResponse]
  implicit val entityDecoder: EntityDecoder[IO, HttpResponse] = jsonOf[IO, HttpResponse]
  implicit val entityEncoder: EntityEncoder[IO, HttpResponse] = jsonEncoderOf[IO, HttpResponse]
}
