package hacedor.todo_app.service;

import hacedor.todo_app.domain.Todo;
import hacedor.todo_app.domain.TodoStatus;
import hacedor.todo_app.model.TodoDTO;
import hacedor.todo_app.repos.TodoRepository;
import hacedor.todo_app.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> findAll() {
        final List<Todo> todos = todoRepository.findAll(Sort.by("id"));
        return todos.stream()
                .map(todo -> mapToDTO(todo, new TodoDTO()))
                .toList();
    }

    public TodoDTO get(final Long id) {
        return todoRepository.findById(id)
                .map(todo -> mapToDTO(todo, new TodoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Todo create(final TodoDTO todoDTO) {
        final Todo todo = new Todo();
        mapToEntity(todoDTO, todo);
        return todoRepository.save(todo);
    }

    public void update(final Long id, final TodoDTO todoDTO) {
        final Todo todo = todoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(todoDTO, todo);
        todoRepository.save(todo);
    }

    public void delete(final Long id) {
        todoRepository.deleteById(id);
    }

    private TodoDTO mapToDTO(final Todo todo, final TodoDTO todoDTO) {
        todoDTO.setId(todo.getId());
        todoDTO.setTitle(todo.getTitle());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setCreatedDate(todo.getCreatedDate());
        todoDTO.setEta(todo.getEta());
        todoDTO.setFinished(todo.getFinished());
        return todoDTO;
    }

    private Todo mapToEntity(final TodoDTO todoDTO, final Todo todo) {
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setEta(todoDTO.getEta());
        todo.setCreatedDate(LocalDateTime.now());
        todo.setFinished(false);
        todo.setTodoStatus(TodoStatus.ON_TIME);
        return todo;
    }

}
