package lab.trading.risk.dto;

public final class RiskCheckResponse {

    private final boolean approved;
    private final String reasonCode;
    private final String reasonMessage;

    public RiskCheckResponse(boolean approved, String reasonCode, String reasonMessage) {
        this.approved = approved;
        this.reasonCode = reasonCode;
        this.reasonMessage = reasonMessage;
    }

    public static RiskCheckResponse approve() {
        return new RiskCheckResponse(true, null, null);
    }

    public static RiskCheckResponse reject(String reasonCode, String reasonMessage) {
        return new RiskCheckResponse(false, reasonCode, reasonMessage);
    }

    public boolean approved() {
        return approved;
    }

    public String reasonCode() {
        return reasonCode;
    }

    public String reasonMessage() {
        return reasonMessage;
    }
}
