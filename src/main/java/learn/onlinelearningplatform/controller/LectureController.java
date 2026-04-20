package learn.onlinelearningplatform.controller;

import learn.onlinelearningplatform.Service.LectureService;
import learn.onlinelearningplatform.dto.lecture.LectureCreateDto;
import learn.onlinelearningplatform.dto.lecture.LecturePatchDto;
import learn.onlinelearningplatform.dto.lecture.LectureResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureResponseDto> createLecture
            (@RequestBody LectureCreateDto dto){
        LectureResponseDto response = lectureService.createLecture(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureResponseDto> getLectureById
            (@PathVariable Long id) {
        LectureResponseDto lecture = lectureService.getLectureById(id);
        return ResponseEntity.ok(lecture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LectureResponseDto> updateLecture
            (@PathVariable Long id,
            @RequestBody LectureCreateDto dto) {

        LectureResponseDto updated = lectureService.updateLecture(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LectureResponseDto> patchLecture(
            @PathVariable Long id,
            @RequestBody LecturePatchDto dto) {

        LectureResponseDto updated = lectureService.patchLecture(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }
}



