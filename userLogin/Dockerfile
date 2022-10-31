FROM openjdk:11

RUN mkdir userLogin
RUN mkdir userLogin/bin

WORKDIR userLogin/bin

ENV PORT 8443

COPY ./target/classes /userLogin/bin/classes
COPY ./target/dependency /userLogin/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.allSecureApp.userLogin.UserLoginApplication"]