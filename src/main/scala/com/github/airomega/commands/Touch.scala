package com.github.airomega.commands

import com.github.airomega.files.{DirEntry, File}
import com.github.airomega.filesystem.State

class Touch(name:String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
