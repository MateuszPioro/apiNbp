package nbp;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRate {
    private String country;
    private String currency;
    private String code;
    private Double mid;
    private Double bid;
    private Double ask;

    @Override
    public String toString() {
        return "ExchangeRate{" +
                (country != null ? "country='" + country + '\'' : "") +
                (currency != null ? ", currency='" + currency + '\'' : "") +
                (code != null ? ", code='" + code + '\'' : "") +
                (mid != null ? ", mid=" + mid : "") +
                (bid != null ? ", bid=" + bid : "") +
                (ask != null ? ", ask=" + ask : "") +
                '}';
    }
}
