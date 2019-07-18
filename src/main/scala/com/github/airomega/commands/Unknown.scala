package com.github.airomega.commands
import com.github.airomega.filesystem.State

class Unknown extends Command {

  override def apply(state: State): State = state.setMessage("Command not found")


}
