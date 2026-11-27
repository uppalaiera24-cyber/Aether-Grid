
package energy;

import java.util.ArrayList;

public class EnergyService {

    private EnergyDAO energyDAO;

    // Constructor
    public EnergyService() {
        energyDAO = new EnergyDAO();
    }

    // Add a new grid condition
    public boolean addGridCondition(GridCondition condition) {

        if (condition == null) {
            System.out.println("Grid condition cannot be empty.");
            return false;
        }

        if (condition.getDataCentreId() <= 0) {
            System.out.println("Invalid Data Centre ID.");
            return false;
        }

        if (condition.getRecordedAt() == null) {
            System.out.println("Recording time is required.");
            return false;
        }

        if (condition.getElectricityPrice() < 0) {
            System.out.println("Electricity price cannot be negative.");
            return false;
        }

        if (condition.getCarbonIntensity() < 0) {
            System.out.println("Carbon intensity cannot be negative.");
            return false;
        }

        if (condition.getRenewablePercentage() < 0
                || condition.getRenewablePercentage() > 100) {

            System.out.println(
                    "Renewable percentage must be between 0 and 100."
            );
            return false;
        }

        return energyDAO.addGridCondition(condition);
    }

    // Get the latest grid condition for a data centre
    public GridCondition getLatestGridCondition(int dataCentreId) {

        if (dataCentreId <= 0) {
            System.out.println("Invalid Data Centre ID.");
            return null;
        }

        return energyDAO.getLatestGridCondition(dataCentreId);
    }

    // Get all recorded grid conditions
    public ArrayList<GridCondition> getAllGridConditions() {

        return energyDAO.getAllGridConditions();
    }
}

