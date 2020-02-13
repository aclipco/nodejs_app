properties([[$class: 'JiraProjectProperty'], parameters([string(defaultValue: 'v1', description: 'Please provide the version number', name: 'APP_VERSION', trim: false)])])

node {
   stage("pull repo"){
     git 'https://github.com/aclipco/nodejs_app.git'

   }


stage("build,Tag Image Image"){
  sh "docker build -t app1:${APP_VERSION} . "
   }
stage("Image Tag"){
sh '''docker tag app1:${APP_VERSION} 431846644568.dkr.ecr.us-east-1.amazonaws.com/app1:${APP_VERSION}'''

}

stage("Login to ECR"){
sh '''$(aws ecr get-login --no-include-email --region us-east-1)'''
   }

stage("push image"){
  sh "docker images"
sh "docker push 431846644568.dkr.ecr.us-east-1.amazonaws.com/app1:${APP_VERSION}"
   }

}
