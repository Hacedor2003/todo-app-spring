package hacedor.todo_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

import hacedor.todo_app.domain.TodoStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime eta;

    private Boolean finished;

    private TodoStatus todoStatus;

}
