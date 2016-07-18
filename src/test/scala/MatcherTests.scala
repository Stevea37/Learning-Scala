import org.scalatest.FlatSpec
import java.io.File


class MatcherTests extends FlatSpec
{
    "Matcher that is passed a file matching the filter" should
    "return a list with that file name" in
    {
        val results = Matcher("fake", "fakePath").execute()
        assert(results == List("fakePath"))
    }

    "Matcher using a directory contain one file matching the filter" should
    "return a list with that file name" in
    {
        val results = Matcher("readme.txt", new File(".\\testfiles\\").getCanonicalPath()).execute()
        assert(results == List("readme.txt"))
    }

    "Matcher that is not passed a root file location" should
    "use the current location" in
    {
        assert(Matcher.rootLocation == new File(".").getCanonicalPath())
    }

    "Matcher with sub folder checking matching root location with two subtree files matching" should
    "return a list with those file names" in
    {
        val searchSubDirectories = true
        val results = Matcher("txt", new File(".\\testfiles\\").getCanonicalPath(), searchSubDirectories).execute()

        assert(results == List("notes.txt", "readme.txt"))

    }
}