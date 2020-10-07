package common;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;

    /*
    key  -> title, genre
    value -> harryPotter, action
    operation -> >= <= =, ....
     */

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation +
                '}';
    }
}
