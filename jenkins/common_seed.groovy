def baseUrl = "https://github.com/pavanmech/"
def repoName = "$reponame"
def gitRepoUrl= baseUrl + repoName + '.git'
def jobName = "$reponame"
  pipelineJob(jobName) {
        properties {
         pipelineTriggers {
          triggers {
            pollSCM {
              scmpoll_spec('*/1 * * * *')
              ignorePostCommitHooks(true)
            }
          }
         }
        }
        logRotator{
         numToKeep(3)
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitRepoUrl)
                            credentials('git_credentials_new')
                        }
                        branches('main')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }
    }
