
package analytics;

import jdbc.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnergyAnalytics {

    public void displayEnergyReport() {

        String sql = "SELECT COUNT(*) AS total_records, "
                + "COALESCE(SUM(energy_consumed_kwh), 0) AS total_energy, "
                + "COALESCE(SUM(electricity_cost), 0) AS total_cost "
                + "FROM ENERGY_RECORDS";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int records = rs.getInt("total_records");
                double energy = rs.getDouble("total_energy");
                double cost = rs.getDouble("total_cost");

                System.out.println("\n========== ENERGY CONSUMPTION REPORT ==========");
                System.out.println("Total Energy Records : " + records);
                System.out.printf("Total Energy Consumed: %.2f kWh%n", energy);
                System.out.printf("Total Electricity Cost: %.2f%n", cost);

                if (records == 0) {
                    System.out.println("No energy records are available yet.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error generating energy report: " + e.getMessage());
        }
    }
}
