package nbp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeTable {
    private String table;
    private String no;
    private String effectiveDate;
    private String tradingDate;
    private List<ExchangeRate> rates;

    @Override
    public String toString() {
        return "ExchangeTable{" +
                "table='" + table + '\'' +
                ", no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", tradingDate='" + tradingDate + '\'' +
                ", rates=" + rates +
                '}';
    }
}
