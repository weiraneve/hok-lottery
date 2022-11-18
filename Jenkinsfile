pipeline
{
  agent any
  stages {
    stage('Build')
    {
      steps {
        sh"gradle clean build -x test"
      }
    }
      stage('Deploy')
    {
      steps {
         sh" kill -9 \$(lsof -i:8034) "
         sh" JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /root/.jenkins/workspace/hok-lottery/build/libs/lottery-1.0.3.jar & "
      }
    }
  }
}
