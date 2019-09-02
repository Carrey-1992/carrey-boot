DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

DELETE FROM tb_user;

INSERT INTO tb_user (id, user_name, password, name, age, sex, birthday,created) VALUES
('1', 'zpc', '123456', '鹏程', '22', '1', '1990-09-02',CURRENT_TIMESTAMP),
('2', 'hj', '123456', '静静', '22', '1', '1993-09-05',CURRENT_TIMESTAMP);

