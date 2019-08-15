package com.github.airomega.files

import com.github.airomega.filesystem.FilesystemException

class File(override val parentPath:String, override val name:String, contents:String)
  extends DirEntry(parentPath,name){

  def asDirectory: Directory = throw new FilesystemException("file cannot be coverted to dir")

  override def isDirectory: Boolean = false
  override def isFile: Boolean = true

  def getType: String = "File"

  def asFile: File = this
}


object File {
  def empty(parentPath:String, name:String):File = new File(parentPath, name, "")

}

