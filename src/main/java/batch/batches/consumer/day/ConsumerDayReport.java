package batch.batches.consumer.day;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumerDayReport implements Serializable {
    private long customerId, customerAppId;
    private Long salesId;
    private int businessType;
    private Date calcDay;
    private BigDecimal totalFee, totalProfit;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerAppId() {
        return customerAppId;
    }

    public void setCustomerAppId(long customerAppId) {
        this.customerAppId = customerAppId;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public Date getCalcDay() {
        return calcDay;
    }

    public void setCalcDay(Date calcDay) {
        this.calcDay = calcDay;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }
}
