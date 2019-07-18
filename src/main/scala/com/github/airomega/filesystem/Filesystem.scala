package com.github.airomega.filesystem

import java.util.Scanner

import com.github.airomega.commands.Command
import com.github.airomega.files.Directory

object Filesystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while(true){
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
}
