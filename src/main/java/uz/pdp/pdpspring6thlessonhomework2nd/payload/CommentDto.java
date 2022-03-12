package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull(message = "comment bo'sh bo'lmasligi kerak")
    private String comment;
    private Long postId;



}
