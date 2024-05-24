package id.co.xl.task.subscribertransaction.repository;

import id.co.xl.task.subscribertransaction.model.entity.TransactionDetail;
import id.co.xl.task.subscribertransaction.model.entity.TransactionSummary;

import java.util.List;
import java.util.Map;

public interface TransactionRepository {
    List<TransactionDetail> fetchByMSISDN(String msisdn);
    List<TransactionSummary> getAllMonthlyTransactionSummary();
}
