package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {

    @NotNull(message = "Tittle bo'sh bo'lmasligi kerak")
    private String title;

    @NotNull(message = "Text bo'sh bo'lmasligi kerak")
    private String text;


}
