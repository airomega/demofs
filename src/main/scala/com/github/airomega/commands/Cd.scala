package com.github.airomega.commands
import com.github.airomega.files.{DirEntry, Directory}
import com.github.airomega.filesystem.State

import scala.annotation.tailrec

class Cd (dir: String) extends Command {

  override def apply(state: State): State = {

    //find root
    val root = state.root
    val wd = state.wd

    //find absoulte path of new dir
    val absPath =
      if (dir.startsWith(Directory.SEPARATOR)) dir
      else if (wd.isRoot) wd.path + Directory.SEPARATOR + dir
      else wd.path + dir

    //find dir to cd to
    val destinationDirectory = doFindEntry(root, absPath)

    //change state to new dir
  if (destinationDirectory == null || destinationDirectory.isDirectory)
    state.setMessage(dir+": no such directory")
  else
    State(root, destinationDirectory.asDirectory)

  }

  def doFindEntry(root:Directory, path:String):DirEntry = {
    @tailrec
    def findEntryHelper(currentDirectory: Directory, path: List[String]):Directory = {
      if (path.isEmpty || path.head.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.findEntry(path.head)
      else {
        val nextDir = currentDirectory.findEntry(path.head)
        if (nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory, path.tail)
      }
    }

    // tokens
    val tokens: List[String] = path.substring(1).split(Directory.SEPARATOR).toList
    //navigate to correct entry
    findEntryHelper(root, tokens)

  }
}
