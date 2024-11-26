package org.buildcode.ride_search_service.api.model.v1_0;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Owner {

    @Schema(description = "Owner name", example = "Bob Alice")
    private String name;

    @Schema(description = "Owner email", example = "bob.alice@gmail.com")
    private String email;

    @Schema(description = "Owner display picture", example = "picURL")
    private String picture;

    @Schema(description = "Owner contact number", example = "837492XXXX")
    private String contactNo;
}
