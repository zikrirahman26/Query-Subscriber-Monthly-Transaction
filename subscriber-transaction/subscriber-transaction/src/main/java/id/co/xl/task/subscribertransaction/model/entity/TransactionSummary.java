package id.co.xl.task.subscribertransaction.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary {

    private String month;
    private int totalAmount;
    private int totalTransaction;
}
