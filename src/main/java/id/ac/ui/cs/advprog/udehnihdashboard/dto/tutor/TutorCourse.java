package id.ac.ui.cs.advprog.udehnihdashboard.dto.tutor;

import id.ac.ui.cs.advprog.udehnihdashboard.enums.CourseStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TutorCourse {
    private long courseId;
    private String title;
    private String category;
    private BigDecimal price;
    private String tutorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CourseStatus status;
    private int sectionCount;
    private int articleCount;
}
