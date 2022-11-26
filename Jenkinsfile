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
         sh" docker-compose stop "
         sh" docker-compose up -d "
      }
    }
  }
}
