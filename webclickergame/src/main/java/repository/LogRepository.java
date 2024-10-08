package repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Log;

@Repository
public class LogRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Log> logRowMapper;

    public LogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.logRowMapper = (rs, i) -> {
            Log logObject = new Log();
            logObject.setId(rs.getString("id"));
            logObject.setIp(rs.getString("ip"));
            logObject.setDate(rs.getTimestamp("logdate").toLocalDateTime());
            return logObject;
        };
    }

    public List<Log> getAllLog() {
        String sql = "select * from log";

        return jdbcTemplate.query(sql, logRowMapper);
    }

    public List<Log> getLastTenLogs() {
        String sql = "select * from log order by seq desc limit 0,10";

        return jdbcTemplate.query(sql, logRowMapper);
    }

    public void storeLog(Log log) {
        String sql = "insert into log (id,ip) values (?,?)";

        jdbcTemplate.update(sql, log.getId(), log.getIp());
    }
}
