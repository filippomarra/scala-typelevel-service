# Use a base image with preinstalled sbt
FROM hseeberger/scala-sbt:11.0.12_1.5.5_2.13.6

# Set the working directory
WORKDIR /app

# Copy the build.sbt file and sbt configuration files to the working directory
COPY ./build.sbt ./
COPY ./project ./project

# Build the project (this will download the initial dependencies)
RUN sbt compile

# Copy all the source code to the working directory
COPY . .

# Expose the sbt server port
EXPOSE 8080

# Start the sbt server when the container is launched
CMD ["sbt", "run"]
