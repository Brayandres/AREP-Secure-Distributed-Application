FROM openjdk:11

RUN mkdir serverLogin
RUN mkdir serverLogin/bin

WORKDIR serverLogin/bin

ENV PORT 8445

COPY ./target/classes /serverLogin/bin/classes
COPY ./target/dependency /serverLogin/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.allSecureApp.serverLogin.ServerLoginApplication"]