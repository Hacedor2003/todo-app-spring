package hacedor.todo_app.repos;

import hacedor.todo_app.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo, Long> {
}
