package DataAccess;

import CasinoPOJO.Casino;
import Mappers.LeaderboardMapper;
import Mappers.PointsMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Abhishek Karan
 */
public class DataAccessTemplate {

    private JdbcTemplate jdbcTemplateObject;
    Casino casino = null;
    String query = "";

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public List<Casino> getLeaderboardData() {

        query = "select name,points,timestamp from leaderboard order by points DESC,timestamp ASC LIMIT 5";
        return jdbcTemplateObject.query(query, new LeaderboardMapper());

    }//getLeaderboardData()

    public int insert(final String name) {

        query = "insert into leaderboard(name) values(?)";
        //PreparedStatement pstmt=
        KeyHolder holder = new GeneratedKeyHolder();
        //return jdbcTemplateObject.update(query, new Object[]{name});
        int x = jdbcTemplateObject.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }
        }, holder);
        if (x == 1) {
            return holder.getKey().intValue();
        }
        return 0;

    }//insert()

    public int updatePoint(int points, String id) {

        query = "update leaderboard set points=? where _id=?";
        //if (stat == 'G') {
        return jdbcTemplateObject.update(query, new Object[]{points, id});
        //} else if (stat == 'F') {
        //  return jdbcTemplateObject.update(query, new Object[]{0, bank.getAccount_number()});
        //}
        //return 0;
    }//UpdatePoint()

    public List<Casino> getPoints(String id) {

        query = "select points from leaderboard where _id=? LIMIT 1";
        return jdbcTemplateObject.query(query, new Object[]{id}, new PointsMapper());

    }//getLeaderboardData()

    /*
     public List<Bank> fetchpayTrans(Bank bank, String pay) {
     query = "select bt.amount,bu.uname,bt.timestamp from bank_trans bt,bank_user bu "
     + "where bt.payer=bu.uid and " + pay + "=? ";
     return jdbcTemplateObject.query(query, new Object[]{bank.getPay()}, new TransactionsMapper());

     }//fetchPayTrans()

     public int updateAmount(Bank bank) {
     query = "update bank_user set balance=balance+? where account_no=?";
     if (jdbcTemplateObject.update(query, new Object[]{bank.getAmount(), bank.getAccount_number()}) == 0) {
     return 0;
     } else {
     query = "update bank_user set balance=balance-? where uid=?";
     if (jdbcTemplateObject.update(query, new Object[]{bank.getAmount(), bank.getUid()}) == 0) {
     return 0;
     } else {
     return 1;
     }
     }
     }//updateAmt()

     public int updateBankTrans(Bank bank) {
     query = "select uid from bank_user where account_no=?";
     List<Bank> lst = jdbcTemplateObject.query(query, new Object[]{bank.getAccount_number()}, new CheckMapper());
     int payer = 0;
     for (Bank b : lst) {
     payer = b.getUid();
     }

     query = "insert into bank_trans(payee,payer,amount) values(?,?,?)";
     if (jdbcTemplateObject.update(query, new Object[]{bank.getUid(), payer, bank.getAmount()}) == 0) {
     return 0;
     }
     return 1;
     }//updatetrans()
     */
}//DataAccessTemplate
