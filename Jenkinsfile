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
         sh" JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /root/.jenkins/workspace/lottery/build/libs/lottery-1.0.2.jar & "
      }
    }
  }
}
