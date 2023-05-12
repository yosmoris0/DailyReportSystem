INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ktaro", "$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S", "管理者", 1);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ttaro", "$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56", "一般", 2);
INSERT INTO report(report_date, title, content, employee_id, created_at, updated_at) VALUES ("2023-05-07", "休暇", "ああああああああああああ", 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,);
INSERT INTO report(report_date, title, content, employee_id, created_at, updated_at) VALUES ("2023-05-08", "仕事", "いいいいいいいいいいいい", 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,);
INSERT INTO report(report_date, title, content, employee_id, created_at, updated_at) VALUES ("2023-05-10", "仕事", "おおおおおおおおおおおお", 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,);