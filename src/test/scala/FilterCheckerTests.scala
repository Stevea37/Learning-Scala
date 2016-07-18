import org.scalatest.FlatSpec
import java.io.File


class FilterCheckerTests extends FlatSpec {
    "FilterChecker passed a list where one file matches the filter" should
    "return a ist with that file" in {
        val listOfFiles = List(FileObject(new File("random")), FileObject(new File("match")))
        val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
        assert(matchedFiles == List(FileObject(new File("match"))))
    }
  
    "FilterChecker passed a list with a directory that matches the filter" should
    "should not be return the directory" in {
        val listOfIOObjects = List(FileObject(new File("random")), DirectoryObject(new File("match")))
        val matchedFiles = FilterChecker("match") findMatchedFiles listOfIOObjects
        assert(matchedFiles.length == 0)
    }

    "FilterChecker passed a file with content that matches the filter" should
    "return that the match passed" in {
        val isContentMatched = FilterChecker("pluralsight")
                                    .matchesFileContent(new File("./testfiles/pluralsight.data"))

        assert(isContentMatched == true)
    }

    "FilterChecker passed a file with content that does not match the filter" should
        "return that the match failed" in {
            val isContentMatched = FilterChecker("pluralsight")
                .matchesFileContent(new File("./testfiles/readme.txt"))

            assert(isContentMatched == false)
        }
}