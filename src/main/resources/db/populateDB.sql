DELETE FROM user_roles;
DELETE FROM menu_items;
DELETE FROM votes;
DELETE FROM menu_items_history;
DELETE FROM votes_history;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, enabled, password) VALUES
  ('User1', TRUE , 'password1'),
  ('User2',TRUE ,'password2'),
  ('Admin', TRUE , 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES
  ('Hagen'),
  ('Domino'),
  ('Chaplin');

INSERT INTO menu_items (restaurant_id,name,price) VALUES
  (100003,'Potato',12000),
  (100003,'Soup',20000),
  (100003,'Fry chicken',35000),
  (100004,'Hamburger',10000),
  (100004,'Potato',13000),
  (100004,'Boiled eggs',15000),
  (100005,'Beef stew',40000),
  (100005,'Baked turkey',8000),
  (100005,'Scrambled eggs',12000);

INSERT INTO votes (user_id, restaurant_id) VALUES
  (100000,100003),
  (100001,100004),
  (100002,100003);

