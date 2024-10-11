USE
selise_banking;

CREATE TABLE client_account
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number        VARCHAR(20)    NOT NULL,
    full_name             VARCHAR(100)   NOT NULL,
    date_of_birth         DATE           NOT NULL,
    account_type          VARCHAR(20)    NOT NULL,
    account_status        VARCHAR(20)    NOT NULL,
    balance               DECIMAL(15, 2) NOT NULL,
    last_transaction_date TIMESTAMP,
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);