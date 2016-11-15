package Mappers;

import CasinoPOJO.Casino;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Abhishek Karan
 */
public class PointsMapper implements RowMapper<Casino> {

    @Override
    public Casino mapRow(ResultSet rs, int i) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Casino casino = new Casino();
        casino.setPoints(rs.getInt(1));
        return casino;

    }

}//class
