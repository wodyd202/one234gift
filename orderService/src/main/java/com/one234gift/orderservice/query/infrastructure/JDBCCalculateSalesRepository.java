package com.one234gift.orderservice.query.infrastructure;

import com.one234gift.orderservice.query.application.CalculateSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
@Transactional(readOnly = true)
public class JDBCCalculateSalesRepository implements CalculateSalesRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    /**
     * 해당 월 누적 매출 가져오기
     */
    private final String FIND_CUMULATIVE_SALES_QUERY
    = "SELECT sum(sale_price) as cumulativeSales FROM orders WHERE sales_user_phone = ? AND state != 'CENCEL' AND PARSEDATETIME(create_datetime, 'yyyy-MM') = ?";
    public Long findCumulativeSalesByUserId(String userId, LocalDate date) {
        Long result  = jdbcTemplate.queryForObject(FIND_CUMULATIVE_SALES_QUERY, new String[]{userId, date.format(DateTimeFormatter.ofPattern("yyyy-MM"))}, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("cumulativeSales");
            }
        });
        return result == null ? 0 : result;
    }

    public void findCumulativeSales(LocalDate now) {

    }
}
