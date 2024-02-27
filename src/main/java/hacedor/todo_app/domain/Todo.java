package hacedor.todo_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Todo {

        @Id
        @Column(nullable = false, updatable = false)
        @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String description;

        @Column
        private LocalDateTime createdDate;

        @Column
        private LocalDateTime eta;

        @Column
        private Boolean finished;

        @CreatedDate
        @Column(nullable = false, updatable = false)
        private OffsetDateTime dateCreated;

        @LastModifiedDate
        @Column(nullable = false)
        private OffsetDateTime lastUpdated;

        @Column
        private TodoStatus todoStatus;

}
