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
         sh"nohup java -jar /root/.jenkins/workspace/lottery/build/libs/lottery-1.0.1.jar &"
      }
    }
  }
}
