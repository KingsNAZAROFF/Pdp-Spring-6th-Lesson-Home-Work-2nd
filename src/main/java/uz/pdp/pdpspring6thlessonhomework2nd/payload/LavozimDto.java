package uz.pdp.pdpspring6thlessonhomework2nd.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pdpspring6thlessonhomework2nd.entitiy.enums.Permissions;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LavozimDto {
    @NotBlank
    private String name;


    private String description;

    @NotEmpty
    private List<Permissions> huquqList;
}
