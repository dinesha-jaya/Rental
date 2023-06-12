package lk.rental.dto;


import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentStartDTO {

    private long rentId;
    private String status;
    private String remarks;

    private ArrayList<RentStartCarDTO> rentStartCarDTOs = new ArrayList<>();




}
