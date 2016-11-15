package Mappers;

import CasinoPOJO.Casino;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Abhishek Karan
 */
public class LeaderboardMapper implements RowMapper<Casino> {
    
    @Override
    public Casino mapRow(ResultSet rs, int i) throws SQLException {
        
        Casino casino = new Casino();
        casino.setUname(rs.getString(1));
        casino.setPoints(rs.getInt(2));
        casino.setTimestamp(rs.getString(3));
        return casino;
    }
    
}//bankMapper
