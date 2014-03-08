pla
===

Perceptron Learning Algorithm: Supervised Classification

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
