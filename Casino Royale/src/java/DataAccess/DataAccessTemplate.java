package DataAccess;

import CasinoPOJO.Casino;
import Mappers.LeaderboardMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

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

        query = "select name,points,timestamp from leaderboard order by points DESC LIMIT 5";
        return jdbcTemplateObject.query(query, new LeaderboardMapper());

    }//login()

    public int insert(String name) {

        query = "insert into leaderboard(name) values(?)";
        return jdbcTemplateObject.update(query, new Object[]{name});

    }//insert()
/*
     public List<Bank> checkAccNo(Bank bank) {

     query = "select uid from bank_user where account_no=? ";
     return jdbcTemplateObject.query(query, new Object[]{bank.getAccount_number()}, new CheckMapper());

     }//checkAccno()

     public List<Bank> checkUname(Bank bank) {

     query = "select uid from bank_user where uname=? ";
     return jdbcTemplateObject.query(query, new Object[]{bank.getUsername()}, new CheckMapper());

     }//checkUname()

     public int updateAdmin(Bank bank, char stat) {

     query = "update bank_user set status=? where account_no=?";
     if (stat == 'G') {
     return jdbcTemplateObject.update(query, new Object[]{1, bank.getAccount_number()});
     } else if (stat == 'F') {
     return jdbcTemplateObject.update(query, new Object[]{0, bank.getAccount_number()});
     }
     return 0;
     }//UpdateAdmin()

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
