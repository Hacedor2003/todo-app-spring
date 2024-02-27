package hacedor.todo_app.mappers;

import java.time.LocalDateTime;

import hacedor.todo_app.domain.Todo;
import hacedor.todo_app.domain.TodoStatus;
import hacedor.todo_app.model.TodoDTO;

public class Mappers {

  public static final Todo mapToEntity(final TodoDTO todoDTO, final Todo todo) {
    todo.setTitle(todoDTO.getTitle());
    todo.setDescription(todoDTO.getDescription());
    todo.setEta(todoDTO.getEta());
    todo.setCreatedDate(LocalDateTime.now());
    todo.setFinished(false);
    todo.setTodoStatus(TodoStatus.ON_TIME);
    return todo;
  }

}
