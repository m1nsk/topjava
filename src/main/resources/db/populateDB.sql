DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, '2015-05-30T10:00', 'test1', 1000),
  (100000, '2015-05-30T13:00', 'test1', 2000),
  (100000, '2015-05-31T10:00', 'test1', 3000),
  (100000, '2015-05-31T13:00', 'test1', 4000),
  (100001, '2015-05-30T10:00', 'test2', 1000),
  (100001, '2015-05-30T13:00', 'test2', 2000),
  (100001, '2015-05-31T10:00', 'test2', 3000),
  (100001, '2015-05-31T13:00', 'test2', 4000);