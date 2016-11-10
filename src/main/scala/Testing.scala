import breeze.linalg._
import breeze.stats.distributions._
import org.ddahl.rscala.RClient

object ScalaToRTest extends App {
  // first simulate some data consistent with a Poisson regression model
  val x = Uniform(50, 60).sample(1000)
  val eta = x map { xi => (xi * 0.1) - 3 }
  val mu = eta map {
    math.exp(_)
  }
  val y = mu map {
    Poisson(_).draw
  }

  // call to R to fit the Poission regression model
  val R = RClient() // initialise an R interpreter
  R.x = x.toArray // send x to R
  R.y = y.toArray // send y to R
  R.eval("mod <- glm(y~x,family=poisson())")
  // fit the model in R
  // pull the fitted coefficents back into scala
  val beta = DenseVector[Double](R.evalD1("mod$coefficients"))

  // print the fitted coefficents
  println(beta)

  // a slightly bigger example

  R.eval(
    """
      |evaluateModel <- function(data,results) {
      |    # data: real  column(actual values)
      |    # results: predicted column (predicted values)
      |
      |    confMatrix <- table(data,results)
      |    print(confMatrix)
      |
      |    err <- (confMatrix["J","N"]+confMatrix["N","J"])/sum(confMatrix)
      |    acc <- (confMatrix["J","J"]+confMatrix["N","N"])/sum(confMatrix)
      |
      |    kappa <- vcd::Kappa(confMatrix)
      |    kappa <- kappa$Unweighted[1]
      |
      |    names(kappa) <- c("kappa")
      |    names(err) <- c("Error rate")
      |    names(acc) <- c("Accuracy")
      |
      |    results <- list(err,acc, kappa)
      |    results
      |}
      |
      |evaluateAllTheThings <- function(groundTruth, prediction){
      |    f1 <- MLmetrics::F1_Score(y_pred = prediction, y_true = groundTruth)
      |    auc <- MLmetrics::AUC(y_pred = prediction, y_true = groundTruth)
      |    names(f1) <- c("f1_R")
      |    names(auc) <- c("AUC_R")
      |
      |    predictionJN <- ifelse(prediction == 0,"N","J")
      |    groundTruthJN <- ifelse(groundTruth == 0,"N","J")
      |
      |    evalA <- evaluateModel(groundTruthJN,predictionJN)
      |
      |    index <- length(evalA)+1
      |
      |    evalA[[index]] <- f1
      |    evalA[[index+1]] <- auc
      |    evalA
      |}
    """.stripMargin
  )

  val snippet = "evalResult <- evaluateAllTheThings(groundTruth, prediction)"
  R.set("groundTruth", Array(0, 1, 0, 0, 1))
  R.set("prediction", Array(0, 1, 1, 0, 0))
  R.eval(snippet, false)

  R.get("evalResult")
  R.getD0("evalResult")
}
