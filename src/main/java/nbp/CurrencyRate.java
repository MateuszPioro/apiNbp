package nbp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyRate {
    private String no;
    private String effectiveDate;
    private Double mid;
    private Double bid;
    private Double ask;

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                (mid != null ? ", mid=" + mid : "") +
                (bid != null ? ", bid=" + bid : "") +
                (ask != null ? ", ask=" + ask : "") +
                '}';
    }
}

