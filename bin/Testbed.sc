

object Testbed {




    val alongli = List("1","2","3","4","5","6")

    def recurse(head: List[Any],alreadyProcessed: List[Any]): List[Any] = head match {
            case List() => List()
            case head :: tail =>
                for(x <- head) println(x)
                recurse(tail, head :: alreadyProcessed)
    }





    def execute(head : List[Any]) =
    {
        recurse(head,List())
    }

    execute(alongli)


}