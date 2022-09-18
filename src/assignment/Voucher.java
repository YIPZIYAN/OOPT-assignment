package assignment;

/**
 *
 * @author Yip Zi Yan
 */
import java.time.LocalDate;

public class Voucher {

    private String voucherCode;
    private double discountRate;
    private double minSpend;
    private double capValue;
    private LocalDate expireDate;

    public Voucher() {
    }

    public Voucher(String voucherCode, double discountRate, double minSpend, double capValue, LocalDate expireDate) {
        this.voucherCode = voucherCode;
        this.discountRate = discountRate;
        this.minSpend = minSpend;
        this.capValue = capValue;
        this.expireDate = expireDate;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getMinSpent() {
        return minSpend;
    }

    public double getCapValue() {
        return capValue;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setMinSpent(double minSpend) {
        this.minSpend = minSpend;
    }

    public void setCapValue(double capValue) {
        this.capValue = capValue;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isValid(String voucherCode) {
        if (this.voucherCode.equals(voucherCode)) {
            if (expireDate.isAfter(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMinSpend(double spend) {
        return spend >= minSpend;
    }

    @Override
    public String toString() {
        return String.format("%s %.1f%% RM%.2f RM%.2f %s", voucherCode, discountRate, minSpend, capValue, expireDate);
    }

}
