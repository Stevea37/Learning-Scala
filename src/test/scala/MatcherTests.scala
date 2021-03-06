import org.scalatest.FlatSpec
import java.io.File


class MatcherTests extends FlatSpec {

    "Matcher that is passed a file matching the filter" should
    "return a list with that file name" in {
        val results = new Matcher("fake", "fakePath").execute()
        assert(results == List(("fakePath", None)))
    }

    "Matcher using a directory contain one file matching the filter" should
    "return a list with that file name" in {
        val results =  new Matcher("readme.txt", new File(".\\testfiles\\").getCanonicalPath()).execute()
        assert(results == List(("readme.txt", None)))
    }

    "Matcher that is not passed a root file location" should
    "use the current location" in {
        val matcher = new Matcher("filter")
        assert(matcher.rootLocation == new File(".").getCanonicalPath())
    }

    "Matcher with sub folder checking matching root location with two subtree files matching" should
    "return a list with those file names" in {
        val searchSubDirectories = true
        val results = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath(), searchSubDirectories).execute()

        assert(results == List(("notes.txt", None),("readme.txt", None)))
    }

    "Matcher given a path that has one file that matches file filter and content filter" should
    "return a list with that file name" in {
        val matchedFiles = new Matcher("data",new File(".\\testfiles\\").getCanonicalPath(), true, Some("pluralsight")).execute()

        assert(matchedFiles == List(("pluralsight.data", Some(3))))
    }

    "Matcher has given a path that has no file that matches file filter and content filter" should
    "return an empty list" in {
        val matchedFiles = new Matcher("txt",new File(".").getCanonicalPath(), true, Some("pluralsight")).execute()

        assert(matchedFiles == List())
    }
}