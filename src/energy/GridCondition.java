
package energy;

import java.time.LocalDateTime;

public class GridCondition {

    private int conditionId;
    private int dataCentreId;
    private LocalDateTime recordedAt;

    private double electricityPrice;
    private double carbonIntensity;
    private double renewablePercentage;

    // Default constructor
    public GridCondition() {
    }

    // Parameterized constructor
    public GridCondition(int conditionId, int dataCentreId,
            LocalDateTime recordedAt, double electricityPrice,
            double carbonIntensity, double renewablePercentage) {

        this.conditionId = conditionId;
        this.dataCentreId = dataCentreId;
        this.recordedAt = recordedAt;
        this.electricityPrice = electricityPrice;
        this.carbonIntensity = carbonIntensity;
        this.renewablePercentage = renewablePercentage;
    }

    // Getters and Setters

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public int getDataCentreId() {
        return dataCentreId;
    }

    public void setDataCentreId(int dataCentreId) {
        this.dataCentreId = dataCentreId;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public double getCarbonIntensity() {
        return carbonIntensity;
    }

    public void setCarbonIntensity(double carbonIntensity) {
        this.carbonIntensity = carbonIntensity;
    }

    public double getRenewablePercentage() {
        return renewablePercentage;
    }

    public void setRenewablePercentage(double renewablePercentage) {
        this.renewablePercentage = renewablePercentage;
    }

    // Display grid condition details
    public void displayDetails() {

        System.out.println("\n--- GRID CONDITION DETAILS ---");

        System.out.println("Condition ID: " + conditionId);
        System.out.println("Data Centre ID: " + dataCentreId);
        System.out.println("Recorded At: " + recordedAt);
        System.out.println("Electricity Price: " + electricityPrice);
        System.out.println("Carbon Intensity: " + carbonIntensity);
        System.out.println("Renewable Energy: "
                + renewablePercentage + "%");
    }
}
