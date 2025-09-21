CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    authority VARCHAR(100),
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS work_schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    employee_id INT UNIQUE,
    statistics_id INT UNIQUE,
    CONSTRAINT fk_work_schedule_employee FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS statistics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    failures INTEGER
);

CREATE TABLE IF NOT EXISTS media (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    path VARCHAR(255),
    type VARCHAR(15),
    day_time VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(300) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

ALTER TABLE work_schedules
ADD CONSTRAINT fk_statistics FOREIGN KEY (statistics_id) REFERENCES statistics(id);

ALTER TABLE statistics
ADD CONSTRAINT fk_work_schedule FOREIGN KEY (id) REFERENCES work_schedules(statistics_id);







