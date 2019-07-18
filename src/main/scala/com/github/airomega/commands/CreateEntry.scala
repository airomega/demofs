package com.github.airomega.commands

import com.github.airomega.files.{DirEntry, Directory}
import com.github.airomega.filesystem.State

abstract class CreateEntry(name:String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry "+name+" already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name+" must not contain separators")
    } else if (checkIllegal(name)){
      state.setMessage(name + ": illegal name")
    } else {
      doCreateEntry(state, name)
    }
  }


  def checkIllegal(str: String): Boolean = str.contains(".")

  def doCreateEntry(state:State, str: String):State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))

      }
    }

    val wd = state.wd
    val fullPath = wd.path

    //1 all the directories in full path
    val allDirsInPath = wd.getAllFoldersInPath

    //2 create new directory entry

    val newEntry: DirEntry = createSpecificEntry(state)
    //3 update the whole dir structure starting from root (dir structure is IMMUTABLE)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    //4 find new working directory instance given WD's full path
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }


  def createSpecificEntry(state:State):DirEntry
}
