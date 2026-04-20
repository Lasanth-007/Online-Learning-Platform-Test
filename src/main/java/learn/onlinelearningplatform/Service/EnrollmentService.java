package learn.onlinelearningplatform.Service;

import jakarta.transaction.Transactional;
import learn.onlinelearningplatform.Entity.Course;
import learn.onlinelearningplatform.Entity.Enrollment;
import learn.onlinelearningplatform.Entity.User;
import learn.onlinelearningplatform.dto.enrollment.EnrollmentCreateDto;
import learn.onlinelearningplatform.dto.enrollment.EnrollmentResponseDto;
import learn.onlinelearningplatform.embedded.EnrollmentId;
import learn.onlinelearningplatform.mapper.EnrollmentMapper;
import learn.onlinelearningplatform.repositories.CourseRepository;
import learn.onlinelearningplatform.repositories.EnrollmentRepository;
import learn.onlinelearningplatform.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepositories userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Transactional
    public EnrollmentResponseDto createEnrollment
            (EnrollmentCreateDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Prevent duplicate
        if (enrollmentRepository.existsByUser_IdAndCourse_Id(dto.userId(), dto.courseId())) {
            throw new RuntimeException("User is already enrolled in this course");
        }

        Enrollment enrollment = enrollmentMapper.toEntity(dto,user,course);

        EnrollmentId id = new EnrollmentId(user.getId(), course.getId());
        enrollment.setId(id);

        return enrollmentMapper.toResponseDto
                (enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentResponseDto> getEnrollmentsByUser(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUser_Id(userId);
        return enrollmentMapper.toResponseDtoList(enrollments);
    }

    // ==================== GET - Enrollments by Course ====================
    public List<EnrollmentResponseDto> getEnrollmentsByCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(courseId);
        return enrollmentMapper.toResponseDtoList(enrollments);
    }

    // ==================== DELETE Enrollment ====================
    @Transactional
    public void deleteEnrollment(Long userId, Long courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(userId, courseId);

        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new RuntimeException("Enrollment not found");
        }

        enrollmentRepository.deleteById(enrollmentId);
    }
}
