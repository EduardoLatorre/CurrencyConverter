import java.util.HashMap;

public class Currency {
    boolean success;
    long timestamp;
    String base;
    String date;
    Rates rates;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public float connverter(String fromCode, String toCode){
        float currency;

        HashMap<String, Float> currencyvalue = new HashMap<String, Float>();

        currencyvalue.put("USD", rates.getUSD());
        currencyvalue.put("MXN", rates.getMXN());
        currencyvalue.put("AUD", rates.getAUD());
        currencyvalue.put("CAD", rates.getCAD());
        currencyvalue.put("PLN", rates.getPLN());
        currencyvalue.put("EUR", 1F);

        System.out.println(fromCode);
        float fromValue;
        float toValue;

        if (currencyvalue.containsKey(fromCode)){
            fromValue = currencyvalue.get(fromCode);
        }
        else{
            fromValue = 1F;
        }

        if (currencyvalue.containsKey(toCode)){
            toValue = currencyvalue.get(toCode);
        }
        else{
            toValue = 0F;
        }

        currency = toValue / fromValue;

        return currency;
    }
}
