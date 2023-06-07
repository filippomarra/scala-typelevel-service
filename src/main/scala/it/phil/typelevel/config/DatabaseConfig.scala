package it.phil.typelevel.config

import com.typesafe.config.ConfigFactory

case class DatabaseConfig(
  driver: String,
  url: String,
  username: String,
  password: String
)

object DatabaseConfig {
  val conf = ConfigFactory.load()
  val config = {
    val driver = conf.getString("database.driver")
    val url = conf.getString("database.url")
    val username = conf.getString("database.user")
    val password = conf.getString("database.password")
    if (driver.isEmpty || url.isEmpty || username.isEmpty || password.isEmpty) {
      throw new RuntimeException("Missing required environment variables for database configuration")
    }
    DatabaseConfig(driver, url, username, password)
  }
}
