package id.co.xl.task.subscribertransaction.repository.impl;

import id.co.xl.task.subscribertransaction.model.entity.TransactionDetail;
import id.co.xl.task.subscribertransaction.model.entity.TransactionSummary;
import id.co.xl.task.subscribertransaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${query.transaction.fetchbymsisdn}")
    private String queryFetchByMSISDN;

    @Value("${query.transaction.totalAmountAllYears}")
    private String queryTotalAmountAllYears;

    @Value("${query.transaction.totalTransactionsAllYears}")
    private String queryTotalTransactionsAllYears;

    @Override
    public List<TransactionDetail> fetchByMSISDN(String msisdn) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(queryFetchByMSISDN, params, BeanPropertyRowMapper.newInstance(TransactionDetail.class));
    }
    @Override
    public List<TransactionSummary> getAllMonthlyTransactionSummary() {
        List<TransactionSummary> transactionSummaries = namedParameterJdbcTemplate.query(queryTotalAmountAllYears,
                (rs, rowNum) -> {
                    TransactionSummary summary = new TransactionSummary();
                    summary.setMonth(rs.getString("month"));
                    summary.setTotalAmount(rs.getInt("totalAmount"));
                    return summary;
                });

        namedParameterJdbcTemplate.query(queryTotalTransactionsAllYears,
                (rs, rowNum) -> {
                    for (TransactionSummary summary : transactionSummaries) {
                        if (summary.getMonth().equals(rs.getString("month"))) {
                            summary.setTotalTransaction(rs.getInt("totalTransaction"));
                            break;
                        }
                    }
                    return null;
                });

        return transactionSummaries;
    }
}
