# Query-Subscriber-Monthly-Transaction
Query Subscriber Monthly Transaction

SELECT DATE_FORMAT(transaction_date, '%Y-%m') AS month, COUNT(*) AS totalTransaction 
FROM transaction GROUP BY DATE_FORMAT(transaction_date, '%Y-%m') 
ORDER BY month DESC;

SELECT DATE_FORMAT(transaction_date, '%Y-%m') AS month, SUM(amount) AS totalAmount
FROM transaction GROUP BY DATE_FORMAT(transaction_date, '%Y-%m')
ORDER BY month DESC;

#Endpoint
*) http://localhost:8080/api/transaction/count
*) http://localhost:8080/api/transaction/summary
*) https://4k38m.wiremockapi.cloud/subscriber/62819123456/pin
