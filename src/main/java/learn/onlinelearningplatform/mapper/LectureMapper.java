package learn.onlinelearningplatform.mapper;

import learn.onlinelearningplatform.Entity.Lecture;
import learn.onlinelearningplatform.dto.lecture.LecturePatchDto;
import learn.onlinelearningplatform.dto.lecture.LectureResponseDto;
import learn.onlinelearningplatform.dto.lecture.LectureCreateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = ResourceMapper.class   // Important: uses the ResourceMapper we created earlier
)
public interface LectureMapper {

    @Mapping(target = "sectionId", source = "section.id")
    @Mapping(target = "resource", source = "resource")
    LectureResponseDto toResponseDto(Lecture lecture);

    @Mapping(target = "sectionId", source = "section.id")
    @Mapping(target = "resource", source = "resource")
    List<LectureResponseDto> toResponseDtoList(List<Lecture> lectures);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "section", ignore = true)     // set in service
    @Mapping(target = "resource", ignore = true)
    Lecture toEntity(LectureCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "resource", ignore = true)
    void patchLecture(LecturePatchDto dto, @MappingTarget Lecture lecture);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "resource", ignore = true)
    void updateLecture(LectureCreateDto dto, @MappingTarget Lecture lecture);
}