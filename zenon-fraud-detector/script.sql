use zenon_fraud;

CREATE TABLE TRANSACTIONS(
                             id bigint AUTO_INCREMENT PRIMARY KEY,
                             step INT not null,
                             type ENUM('CASH_IN', 'CASH_OUT', 'DEBIT', 'PAYMENT', 'TRANSFER') not null,
                             amount DECIMAL(20,2) not null,
                             nameOrig VARCHAR(50) not null,
                             oldbalanceOrg DECIMAL(20,2) not null,
                             newbalanceOrig DECIMAL(20,2) not null,
                             nameDest VARCHAR(50) not null,
                             oldbalanceDest DECIMAL(20,2) not null,
                             newbalanceDest DECIMAL(20,2) not null,
                             isFraud tinyint(1) default 0,
                             isFlaggedFraud tinyint(1) default 0
);