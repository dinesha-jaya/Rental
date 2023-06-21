package lk.rental.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum UserCategory {

    ADMIN ("admin"),
    CUSTOMER ("customer");

    public String userCategory;
}
