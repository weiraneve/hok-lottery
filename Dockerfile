FROM adoptopenjdk/openjdk11:latest

WORKDIR /app

COPY ./build/libs/hok-lottery-1.0.6.jar /app

ENV LANG=en_US.UTF8
ENV TZ=Asia/Shanghai

EXPOSE 8034

CMD ["java", "-jar", "/app/hok-lottery-1.0.6.jar"]