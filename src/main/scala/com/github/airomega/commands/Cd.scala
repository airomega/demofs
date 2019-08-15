package com.github.airomega.commands
import com.github.airomega.files.Directory
import com.github.airomega.filesystem.State

class Cd (dir: String) extends Command {

  override def apply(state: State): State = {

    //find root
    val root = state.root
    val wd = state.wd

    //find absoulte path of new dir
    val absPath = if (dir.startsWith(Directory.SEPARATOR)) dir
    else if (wd.isRoot) wd.path + Directory.SEPARATOR + dir
    else wd.path + dir

  }
}
