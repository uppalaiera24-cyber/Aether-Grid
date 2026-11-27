
package energy;

import jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EnergyDAO {

    // Add a new grid condition
    public boolean addGridCondition(GridCondition condition) {

        String query = "INSERT INTO GRID_CONDITIONS "
                + "(data_centre_id, recorded_at, electricity_price, "
                + "carbon_intensity, renewable_percentage) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, condition.getDataCentreId());

            ps.setTimestamp(2,
                    Timestamp.valueOf(condition.getRecordedAt()));

            ps.setDouble(3, condition.getElectricityPrice());
            ps.setDouble(4, condition.getCarbonIntensity());
            ps.setDouble(5, condition.getRenewablePercentage());

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error adding grid condition: "
                    + e.getMessage());

            return false;
        }
    }

    // Get the latest grid condition of a data centre
    public GridCondition getLatestGridCondition(int dataCentreId) {

        String query = "SELECT * FROM GRID_CONDITIONS "
                + "WHERE data_centre_id = ? "
                + "ORDER BY recorded_at DESC LIMIT 1";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, dataCentreId);

            ResultSet rs = ps.executeQuery();

            GridCondition condition = null;

            if (rs.next()) {

                condition = new GridCondition(
                        rs.getInt("condition_id"),
                        rs.getInt("data_centre_id"),
                        rs.getTimestamp("recorded_at").toLocalDateTime(),
                        rs.getDouble("electricity_price"),
                        rs.getDouble("carbon_intensity"),
                        rs.getDouble("renewable_percentage")
                );
            }

            rs.close();
            ps.close();
            con.close();

            return condition;

        } catch (SQLException e) {

            System.out.println("Error retrieving latest grid condition: "
                    + e.getMessage());

            return null;
        }
    }

    // Get all grid condition records
    public ArrayList<GridCondition> getAllGridConditions() {

        ArrayList<GridCondition> conditions = new ArrayList<>();

        String query = "SELECT * FROM GRID_CONDITIONS "
                + "ORDER BY recorded_at DESC";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                GridCondition condition = new GridCondition(
                        rs.getInt("condition_id"),
                        rs.getInt("data_centre_id"),
                        rs.getTimestamp("recorded_at").toLocalDateTime(),
                        rs.getDouble("electricity_price"),
                        rs.getDouble("carbon_intensity"),
                        rs.getDouble("renewable_percentage")
                );

                conditions.add(condition);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error retrieving grid conditions: "
                    + e.getMessage());
        }

        return conditions;
    }
}
