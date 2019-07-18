package com.github.airomega.commands
import com.github.airomega.files.Directory
import com.github.airomega.filesystem.State

class Mkdir(name:String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry "+name+" already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
        state.setMessage(name+" must not contain separators")
    } else if (checkIllegal(name)){
        state.setMessage(name + ": illegal name")
    } else {
      doMkdir(state, name)
    }
  }


  def checkIllegal(str: String): Boolean = str.contains(".")
  def doMkdir(state:State, str: String):State = ???

}
