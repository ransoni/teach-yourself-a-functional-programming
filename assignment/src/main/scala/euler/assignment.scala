package euler

object ProjectEuler {
  /*
   * Even Fibonacci numbers
   *
   * Each new term in the Fibonacci sequence is generated by adding the previous
   * two terms. By starting with 1 and 2, the first 10 terms will be:
   *
   * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
   *
   * By considering the terms in the Fibonacci sequence whose values do not
   * exceed four million, find the sum of the even-valued terms.
   */
  def problem2(): Int = {
    var fiblist = List(2,1,0)
    var add = 0

    def fib(fibbis:List[Int]):Int = {
      if ((fiblist.head) > 4000000) {
        //        println("Tulos: " + fibbis.drop(1))
        //println("Summa: " + fibbis.drop(1).filter(x => x % 2 == 0).sum)
        fibbis.drop(1).filter(x => x % 2 == 0).sum
      } else {
        add = fiblist.take(2).head + fiblist.take(2).last
        //println("add: " + add)
        fiblist = add :: fiblist
        //println(fibbis)
        fib(add :: fibbis)
      }
    }

    fib(fiblist)
  }

  /*
   * Largest palindrome product
   *
   * A palindromic number reads the same both ways. The largest palindrome made
   * from the product of two 2-digit numbers is 9009 = 91 × 99.
   *
   * Find the largest palindrome made from the product of two 3-digit numbers.
   *
   */
  def problem4():Int = {
    val a = 999
    val b = 1000
    var pal = 0

//    pyth(a, b)
    // }
    //pyth(2,5)
    def pytha(aaa: Int, bbb: Int): Int = {
      if ((aaa * bbb) == (aaa * bbb).toString.reverse.toInt) {
        //println("Tännekö (pytha) jää? a:" + aaa + " b: " + bbb)
        if ((aaa * bbb) > pal) { pal = aaa * bbb }
          else pal = pal
        if (bbb > 0) {
          pytha(aaa, bbb - 1)
        } else {
          pyth(aaa - 1, b)
        }
      }
      else {
        if (bbb == 0) {
          pyth(aaa - 1, b)
        } else {
          pytha(aaa, bbb - 1)
        }

      }

    } //pytha päättyy

    def pyth(aa: Int, bb: Int): Int = {
      if ((aa * bb) == (aa * bb).toString.reverse.toInt) {

        println("Tännekö (pyth) jää? a:" + aa + " b: " + bb)

        if ((aa * bb) > pal) { pal = aa * bb }
        else pal = pal

        if (aa > 0) {
          pytha(aa,bb-1)
        } else {
          pal
        }
      }
      else {
        if (aa > 0) {
          pytha(aa, bb - 1)
        }
        else {
          555
        }
        //          pyth(a - 1, b - 1)
      }


    } //pyth päättyy

    pyth(a, b)
  } //problem4 päättyy

  //}

  /*
   * Special Pythagorean triplet
   *
   * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
   * which, a^2 + b^2 = c^2
   *
   * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
   *
   * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
   * Find the product abc.
   */
  def problem9(): Int = {
    def pythagora(a:Int,b:Int,c:Int):Int = {
      if ((a + b + c) != 1000) {
        if (a == b) {
          if (b == c) {
            println("3rd level if")
            pythagora(a,b,c+1)
          } else {
            println("sisin else")
            pythagora(a,b+1,c)
          }
        } else {
          println("2nd else")
          pythagora(a+1,b,c)
        }
      } else {
        println("1st level")
        a * b * c
      }
    }

    pythagora(0,0,0)
  }


  /*
   * Maximum path sum I
   *
   * By starting at the top of the triangle below and moving to adjacent numbers
   * on the row below, the maximum total from top to bottom is 23.
   *
   *      3
   *     7 4
   *    2 4 6
   *   8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom of the given triangle with 15
   * rows:
   */
  def problem18(triangle: List[List[Int]]): Int = {

    val row = 0
    val index = 0
    var sum = 0
    val pyra = triangle

    def indexOfMax(tst:List[Int]):Int = {
      //println("indexOfMax")
      var max = tst.max
      tst.indexOf(max)
    }
    def maxPath(listrow:List[List[Int]],ind:Int):Int = { //Kutsutaan tietyn rivin listalla + indexi josta seuraavat kaksi alkiota
      //println("maxPath")
      if (listrow(0).isEmpty) {
        sum
      } else {
        //println("Luku: " + listrow(0)(ind + indexOfMax(listrow(0).drop(ind).take(2))))
        //println("Indeksi: " + (ind + indexOfMax(listrow(0).drop(ind).take(2))))
        sum = sum + listrow(0)(ind + indexOfMax(listrow(0).drop(ind).take(2)))
        //println("Summa nyt: " + sum)
        if (listrow.drop(1).isEmpty) {
          sum
        } else {
          maxPath(listrow.drop(1), ind + indexOfMax(listrow(0).drop(ind).take(2)))
        }
      }

    }

    // pyra(9).drop(indexOfMax(pyra(8)) - 1).take(2)  <-- seuraavan rivin alkiot

    maxPath(triangle,index)


  }

  /*
   * Maximum path sum II
   *
   * By starting at the top of the triangle below and moving to adjacent numbers
   * on the row below, the maximum total from top to bottom is 23.
   *
   *    3
   *   7 4
   *  2 4 6
   * 8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom in the given triangle with
   * one-hundred rows.
   *
   * NOTE: This is a much more difficult version of Problem 18. It is not
   * possible to try every route to solve this problem, as there are 2^99
   * altogether! If you could check one trillion (10^12) routes every second it
   * would take over twenty billion years to check them all. There is an
   * efficient algorithm to solve it. ;o)
   */
  def problem67(triangle: List[List[Int]]): Int = ???
}
