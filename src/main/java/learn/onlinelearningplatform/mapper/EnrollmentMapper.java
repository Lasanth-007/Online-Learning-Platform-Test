package learn.onlinelearningplatform.mapper;

import learn.onlinelearningplatform.dto.enrollment.EnrollmentResponseDto;
import learn.onlinelearningplatform.dto.enrollment.EnrollmentCreateDto;
import learn.onlinelearningplatform.Entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EnrollmentMapper {


    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "courseId", source = "id.courseId")
    @Mapping(target = "userFullName", source = "user.fullName")
    @Mapping(target = "courseTitle", source = "course.title")
    EnrollmentResponseDto toResponseDto(Enrollment enrollment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "enrolledAt", ignore = true)
    Enrollment toEntity(EnrollmentCreateDto dto);
}