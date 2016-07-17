object Testbed {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(60); 
  println("Welcome to the Scala worksheet");$skip(10); val res$0 = 
  "Hello";System.out.println("""res0: String("Hello") = """ + $show(res$0));$skip(27); 
  var yes = assert(5 == 5);System.out.println("""yes  : Unit = """ + $show(yes ))}
  
}
