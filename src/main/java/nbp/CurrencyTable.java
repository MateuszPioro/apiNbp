package nbp;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyTable {
    private String table;
    private String currency;
    private String code;

    private List<CurrencyRate> rates;
}
