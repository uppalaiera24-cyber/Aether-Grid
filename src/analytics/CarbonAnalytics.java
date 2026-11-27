
package analytics;

import jdbc.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarbonAnalytics {

    public void displayCarbonReport() {

        String energySQL = "SELECT COUNT(*) AS total_records, "
                + "COALESCE(SUM(carbon_emissions), 0) AS total_emissions "
                + "FROM ENERGY_RECORDS";

        String gridSQL = "SELECT "
                + "COALESCE(AVG(carbon_intensity), 0) AS avg_carbon, "
                + "COALESCE(AVG(renewable_percentage), 0) AS avg_renewable "
                + "FROM GRID_CONDITIONS";

        try (Connection con = DatabaseConnection.getConnection()) {

            System.out.println("\n========== CARBON ANALYTICS REPORT ==========");

            try (PreparedStatement ps = con.prepareStatement(energySQL);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    int records = rs.getInt("total_records");
                    double emissions = rs.getDouble("total_emissions");

                    System.out.println("Energy Records       : " + records);
                    System.out.printf("Total Carbon Emissions: %.2f%n", emissions);

                    if (records == 0) {
                        System.out.println("No recorded emissions are available yet.");
                    }
                }
            }

            try (PreparedStatement ps = con.prepareStatement(gridSQL);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    double carbonIntensity = rs.getDouble("avg_carbon");
                    double renewable = rs.getDouble("avg_renewable");

                    System.out.printf("Average Grid Carbon Intensity: %.2f%n",
                            carbonIntensity);
                    System.out.printf("Average Renewable Energy: %.2f%%%n",
                            renewable);
                }
            }

            System.out.println("\nNote: These are recorded emissions and grid averages.");
            System.out.println("Carbon abatement is not calculated because no baseline is defined.");

        } catch (SQLException e) {
            System.out.println("Error generating carbon report: " + e.getMessage());
        }
    }
}
