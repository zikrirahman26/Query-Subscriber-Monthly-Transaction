SELECT DATE_FORMAT(transaction_date, '%Y-%m') AS month, COUNT(*) AS totalTransaction 
FROM transaction GROUP BY DATE_FORMAT(transaction_date, '%Y-%m') 
ORDER BY month DESC;

SELECT DATE_FORMAT(transaction_date, '%Y-%m') AS month, SUM(amount) AS totalAmount
FROM transaction GROUP BY DATE_FORMAT(transaction_date, '%Y-%m')
ORDER BY month DESC;


  
