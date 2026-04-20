package Service;

import jakarta.transaction.Transactional;
import learn.onlinelearningplatform.Entity.Lecture;
import learn.onlinelearningplatform.Entity.Resource;
import learn.onlinelearningplatform.Entity.Section;
import learn.onlinelearningplatform.dto.lecture.LectureCreateDto;
import learn.onlinelearningplatform.dto.lecture.LecturePatchDto;
import learn.onlinelearningplatform.dto.lecture.LectureResponseDto;
import learn.onlinelearningplatform.mapper.LectureMapper;
import learn.onlinelearningplatform.mapper.ResourceMapper;
import learn.onlinelearningplatform.repositories.LectureRepository;
import learn.onlinelearningplatform.repositories.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final LectureMapper lectureMapper;
    private final ResourceMapper resourceMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    //POST - Create
    @Transactional
    public LectureResponseDto createLecture(LectureCreateDto dto){

        Section section = sectionRepository.findById(dto.sectionId())
                .orElseThrow(()->new RuntimeException("Section not found"));

        //Create Resource polymorphically using the mapper
        Resource resource = resourceMapper.toEntity(dto.resource());

        Lecture lecture = lectureMapper.toEntity(dto);
        lecture.setSection(section);
        lecture.setResource(resource);

        Lecture savedlecture = lectureRepository.save(lecture);
        return lectureMapper.toResponseDto(savedlecture);
    }

    //Get all lectures in a section
    public List<LectureResponseDto> getLecturesBySection(Long sectionId){

        List<Lecture> lectures = lectureRepository.findBySectionId(sectionId);
            return lectureMapper.toResponseDtoList(lectures);
    }

    //Get by id
    public LectureResponseDto getLectureById(Long id){

        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Lecture not found"));

        return lectureMapper.toResponseDto(lecture);
    }

    @Transactional
    public void deleteLecture(Long id) {
        if (!lectureRepository.existsById(id)) {
            throw new RuntimeException("Lecture not found");
        }
        lectureRepository.deleteById(id);
    }

    //put full update
    @Transactional
    public LectureResponseDto updateLecture(Long id, LectureCreateDto dto) {

        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));

        Section section = sectionRepository.findById(dto.sectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));

        Resource resource = resourceMapper.toEntity(dto.resource());

        lectureMapper.updateLecture(dto, lecture);

        lecture.setSection(section);
        lecture.setResource(resource);

        Lecture saved = lectureRepository.save(lecture);
        return lectureMapper.toResponseDto(saved);
    }

    @Transactional
    public LectureResponseDto patchLecture(Long id, LecturePatchDto dto) {

    Lecture lecture = lectureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lecture not found"));

    lectureMapper.patchLecture(dto, lecture);

    Lecture savedLecture = lectureRepository.save(lecture);
    return lectureMapper.toResponseDto(savedLecture);
    }
}
