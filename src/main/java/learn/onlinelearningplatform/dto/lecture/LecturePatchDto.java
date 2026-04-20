package learn.onlinelearningplatform.dto.lecture;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LecturePatchDto(
        String title,
        Integer durationInMinutes
) {
}
