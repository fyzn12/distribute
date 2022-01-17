FROM store/oracle/jdk:11
ADD kubernetes_demo-1.0.jar kubernetes_demo-1.0.jar
RUN bash -c 'touch /kubernetes_demo-1.0.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","kubernetes_demo-1.0.jar"]
