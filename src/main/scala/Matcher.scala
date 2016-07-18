import java.io.File

class Matcher(filter: String, rootLocation: String = new File(".").getCanonicalPath(),
              checkSubFolders: Boolean = false, contentFilter: Option[String] = None) {
  /** Convert to IO viable object*/
    val rootIOObject = FileConverter.convertToIOObject(new File(rootLocation))


    /** when you use execute, it will check if the rootLocation (changed to rootIOObject) is a file, directory or anything else. */
    def execute() = {
        def recursiveMatch(files: List[IOObject], currentList: List[FileObject]): List[FileObject] = {
            files match {
                    /** If the list of children in the directory equal an empty list, return currentList, which in this instance, is empty */
                case List() => currentList

                case iOObject :: rest =>
                    iOObject match {
                        case file : FileObject if FilterChecker(filter).matches(file.name) =>
                            recursiveMatch(rest, file :: currentList)
                        case directory : DirectoryObject =>
                            recursiveMatch(rest ::: directory.children(), currentList)
                        case _ => recursiveMatch(rest, currentList)
                    }
            }
        }


        val matchedFiles = rootIOObject match {
            /** if rootIOObject is a FileObject and the file name matches the filter string, return as a list. */
            case file :
              FileObject if FilterChecker(filter).matches(file.name) =>
                List(file)

            /** if rootIOObject is a DirectoryObject, find the files in that directory that match the filter string. */
            case directory :
              DirectoryObject =>
                    if(checkSubFolders) {
                        recursiveMatch(directory.children(), List())
                    }
                    else {
                        FilterChecker(filter).findMatchedFiles(directory.children())
                    }
            /** if rootIOObject doesn't match anything, return an empty list. */
                case _ =>
                  List()
        }

        val contentFilteredFiles = contentFilter match {
            case Some(dataFilter) => matchedFiles filter(iOObject =>
                FilterChecker(dataFilter).matchesFileContent(iOObject.file))
            case None => matchedFiles
        }

        contentFilteredFiles map(iOObject => iOObject.name)
    }
}

object Matcher {

    var rootLocation  = new File(".").getCanonicalPath()
    def apply(filter: String,
              rootLocation: String = new File(".").getCanonicalPath(),
              checkSubFolders: Boolean = false,
              contentFilter: Option[String] = None) = new Matcher(filter,
                                          rootLocation,
                                          checkSubFolders,
                                            contentFilter)

}
