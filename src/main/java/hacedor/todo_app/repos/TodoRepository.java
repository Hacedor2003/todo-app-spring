package hacedor.todo_app.repos;

import hacedor.todo_app.domain.Todo;
import hacedor.todo_app.domain.TodoStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

  public java.util.List<Todo> findAllByTodoStatus(TodoStatus status);

  @Modifying
  @Query(value = "UPDATE TODO SET FINISHED=true WHERE ID=:id", nativeQuery = true)
  public void markTodoAsFinished(@Param("id") Long id);
}
