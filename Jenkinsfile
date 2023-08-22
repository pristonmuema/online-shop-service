pipeline {
    agent any
      parameters {
          string(name: 'ENV_TAG', defaultValue: '')
      }
      environment {
             APP_NAME = "online-shop-service"
             JAVA_TOOL_OPTIONS= "--enable-preview"
             IMAGE_BASE_NAME = "${APP_NAME}"
             JAVA_HOME = "/var/lib/jenkins/tools/hudson.model.JDK/jdk17/jdk-17"
             PATH = "${JAVA_HOME}/bin:${env.PATH}"
             TEMP_JAVA_HOME = "/var/lib/jenkins/tools/hudson.model.JDK/jdk17/jdk-17"

      }
      tools {
          jdk 'jdk17'
        }

      stages {
          stage('Maven Build') {
              steps {
                  sh 'java --version'
                  sh 'JAVA_HOME=$TEMP_JAVA_HOME mvn clean compile '

              }
          }

          stage('Maven Package') {
              steps {

                script {
                  if(env.BRANCH_NAME == "staging") {

                  sh '''
                      java --version
                      JAVA_HOME=$TEMP_JAVA_HOME mvn package -Dquarkus.profile=staging -Dmaven.test.skip=true
                     '''
                  } else {
                      sh '''
                          JAVA_HOME=$TEMP_JAVA_HOME mvn package -Dquarkus.profile=prod -Dmaven.test.skip=true
                         '''
                  }
                }
              }
          }

          stage('Maven Tests') {
              steps {
                 script {
                      sh 'JAVA_HOME=$TEMP_JAVA_HOME mvn -Dmaven.test.failure.ignore=true test  -e -s .m2/settings.xml --batch-mode -U dependency:resolve'

                  }
              }
              post {
                  success {
                    junit 'target/surefire-reports/**/*.xml'
                  }
              }
          }

          stage('Code Coverage : Jacoco') {
              steps {
                  publishCoverage adapters: [jacoco(
                      execPattern: 'target/*.exec',
                      classPattern: 'target/classes',
                      sourcePattern: 'src/main/java',
                      exclusionPattern: 'src/test*'
                  )]
              }
          }

          stage('Docker Build & Push Image') {
              when {
                  anyOf {
                      branch "main"; branch "staging"; branch "dev";
                  }
              }
              steps {
                script {
                    if (env.BRANCH_NAME == "master") {
                          env.ENV_TAG = "prod"
                    } else if(env.BRANCH_NAME == "staging") {
                        env.ENV_TAG = "staging"
                    } else {
                          env.ENV_TAG = "dev"
                    }

                  sh '''
                      IMAGE_TAG="${ENV_TAG}_$(date +%Y-%m-%d-%H-%M)"
                      IMAGE_NAME="${IMAGE_BASE_NAME}:${IMAGE_TAG}"
                      docker build -f ./src/main/docker/Dockerfile.jvm -t $IMAGE_NAME .
                      docker push $IMAGE_NAME
                  '''
                }
              }
          }
      }
    }
}