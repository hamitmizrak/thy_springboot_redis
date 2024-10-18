

# JDK
FROM openjdk:17

# Bilgilendirme
LABEL maintainer="hamitmizrak@gmail.com"

# Çevresel değişkenler
ENV APP_NAME="THY - BT "
ENV VERSION="v1.0.0"
ENV PORT="5555"


# Çevresel Değişkenleri RUN
RUN echo "App Name: $APP_NAME"
RUN echo "Version: $VERSION"
RUN echo "Port: $PORT"

# Kalıcılık
VOLUME /tmp


# Port
EXPOSE 5555

# Project Deployment
# RUN mvn clean package -DskipTests

# Proje Jar ismini
ARG JAR_FILE=/target/*.jar

# Proje isimlednirme
ADD ${JAR_FILE} blog


# Uygulama Başlatma Komut dizini
ENTRYPOINT ["java","-jar","/blog"]

#######################################################################################################
# docker image build -t image_adi .
# docker image ls
# docker ps
# docker container run -d -p 5555:4444 --rm --name container_adi image_adi
