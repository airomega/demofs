package com.github.airomega.commands

import com.github.airomega.files.{DirEntry, Directory}
import com.github.airomega.filesystem.State


class Mkdir(name:String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path, name)
}
