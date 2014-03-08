/** Perceptron Learning Algorithm: Supervised Classification

Example: Consider a line with slope 2, intercept 3
1y = 2x + 3
ie. (-3)(1) + (-2)(x1) + (+1)(x2) = 0  //call (x,y) as (x1,x2) in R2
ie. Desired Weights W = Seq(-3,-2,1), with coordinates Seq(1,x1,x2)

Construct n coordinates, some above the line & some below
The ones above have a sign +1, below -1

Start with a weight vector Seq(1,1,1)
Apply PLA Algo until it terminates.

PLA Algo:
--
For all X,
Want sign(w0x0 + w1x1 + w2x2) == sign(X)
For any X, if sign doesn't agree,
Apply Update rule:
W = W + X
--
The weights must be close to Seq(-3,-2,1)
*/
object PLA extends App {
  // high school vector math
  type Weights = Seq[Double]
  def dot(a:Weights,b:Weights) = a.zip(b).map(ab => ab._1 * ab._2).sum
  def plus(a:Weights,b:Weights) = a.zip(b).map(ab => ab._1 + ab._2)
  def prod(a:Weights, b:Double) = a.map(i => i*b)
  def scale(a:Weights) = prod(a, 1.0/a.last)

  // pla update rule
  def update(w:Weights, x:(Weights,Int)) = plus(w,prod(x._1, x._2)) // w = w + sign(x)*x
  def misclassified(w:Weights, x:(Weights,Int)) = math.signum(dot(w, x._1)) != x._2

  def pla(points:Seq[(Weights, Int)]) {
    var notDone = true
    var w = Seq(1.0,1.0,1.0)
    var (iter,updater) = (0,0)
    while(notDone) {
      notDone = false
      points.foreach {

        p => if (misclassified(w, p)) {
          notDone = true
          w = update(w,p)
          updater += 1
        }

        iter += 1
        printf("Iteration %d: Updates: %d \tPoint %s\tWeights %s\n", iter,updater, p._1.mkString(","), w.mkString(","))
      }
    }
    printf("\nWeights: %s\n", scale(w).mkString(","))
  }

  def mkPoints(n:Int) = {
    def line(x:Int) = 2 * x + 3
    val above = (1 to n).toSeq.map{x=> (Seq(1, x, line(x) + math.random), +1 )}
    val below = (1 to n).toSeq.map{x=> (Seq(1, x, line(x) - math.random), -1 )}
    util.Random.shuffle(above ++ below)
  }

  pla(mkPoints(args(0).toInt))
}
