package hacedor.todo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hacedor.todo_app.domain.Todo;
import hacedor.todo_app.domain.TodoStatus;
import hacedor.todo_app.mappers.Mappers;
import hacedor.todo_app.model.TodoDTO;
import hacedor.todo_app.repos.TodoRepository;
import hacedor.todo_app.util.NotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return this.todoRepository.findAll();
    }

    public Todo getById(final Long id) {
        return todoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<Todo> findAllByTodoStatus(TodoStatus status) {
        return this.todoRepository.findAllByTodoStatus(status);
    }

    public Todo create(final TodoDTO todoDTO) {
        final Todo todo = new Todo();
        Mappers.mapToEntity(todoDTO, todo);
        return todoRepository.save(todo);
    }

    public void update(final Long id, final TodoDTO todoDTO) {
        final Todo todo = todoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        Mappers.mapToEntity(todoDTO, todo);
        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodoAsFinished(Long id) {
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        if (optionalTodo.isEmpty()) {
            throw new hacedor.todo_app.exceptions.ToDoExceptions("Todo not found", HttpStatus.NOT_FOUND);
        }

        this.todoRepository.markTodoAsFinished(id);
    }

    public void delete(final Long id) {
        todoRepository.deleteById(id);
    }

}
